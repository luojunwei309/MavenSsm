package com.ljw.ssm.util;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;	

// 通用工具方法
public class CrowdUtil {
	
	
	public static ResultEntity<String> uploadFileToOss(
			InputStream inputStream, 
			String originalName) {
		
		 String endpoint="http://oss-cn-shenzhen.aliyuncs.com";
		String accessKeyId="LTAI4G3LXhvXMsV6zAQ6nuv8";
		String accessKeySecret="Dv7WErWYfwegoJaWhCfOffLP8xEfJZ";
		String bucketName="ljw1998";
		String bucketDomain="http://ljw1998.oss-cn-shenzhen.aliyuncs.com";
		
		// 创建 OSSClient 实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// 生成上传文件的目录
		String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
		 
		// 生成上传文件在 OSS 服务器上保存时的文件名
		// 原始文件名：beautfulgirl.jpg
		// 生成文件名：wer234234efwer235346457dfswet346235.jpg
		// 使用 UUID 生成文件主体名称
		String fileMainName = UUID.randomUUID().toString().replace("-", "");

		// 从原始文件名中获取文件扩展名
		String extensionName = originalName.substring(originalName.lastIndexOf("."));

		// 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
		String objectName = folderName + "/" + fileMainName + extensionName;

		try {
			// 调用 OSS 客户端对象的方法上传文件并获取响应结果数据
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

			// 从响应结果中获取具体响应消息
			ResponseMessage responseMessage = putObjectResult.getResponse();

			// 根据响应状态码判断请求是否成功
			if (responseMessage == null) {
				// 拼接访问刚刚上传的文件的路径
				String ossFileAccessPath = bucketDomain + "/" + objectName;

				// 当前方法返回成功
				return ResultEntity.successWithData(ossFileAccessPath);

			} else {
				// 获取响应状态码
				int statusCode = responseMessage.getStatusCode();

				// 如果请求没有成功，获取错误消息
				String errorMessage = responseMessage.getErrorResponseAsString();

				// 当前方法返回失败
				return ResultEntity.failed(" 当 前 响 应 状 态 码 =" + statusCode + " 错 误 消 息=" + errorMessage);
			}
		} catch (Exception e) {

			e.printStackTrace();
			// 当前方法返回失败
			return ResultEntity.failed(e.getMessage());
		} finally {
			if (ossClient != null) {
				// 关闭 OSSClient。
				ossClient.shutdown();
			}
		}
	}
	
	
	
	// 1.判断是否为ajax请求
	public static boolean ajaxRequestType(HttpServletRequest request) {
           // 1.获取请求头
		String header = request.getHeader("Accept");
		String xreheader = request.getHeader("X-Requested-With");
		// 2.判断
		return (header!=null &&header.contains("application/json"))||(xreheader!=null&&xreheader.equals("XMLHttpRequest"));
	}
	
	// 2.加密
     public static String md5(String source) {
    	 // 判断
    	 if (source==null||source.length()==0) {
			throw new RuntimeException(CrowdConstand.MESSAGE_INVALI);
		}
    	 
    	 // 获取MessageDigest
    	 try {
    		 String AL="md5";
			MessageDigest instance = MessageDigest.getInstance(AL);
			// 获取明文字符串对应的字符数组
			byte[] bytes = source.getBytes();
			// 执行加密
			byte[] digest = instance.digest(bytes);
			// 创建BigInteger
			BigInteger bigInteger = new BigInteger(1,digest);
			// 按照十六进制转换
			int s=16;
			String upperCase = bigInteger.toString(s).toUpperCase();
			return upperCase;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return null;
    	 
     }	

}
