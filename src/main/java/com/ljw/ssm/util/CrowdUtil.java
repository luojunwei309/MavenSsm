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

// ͨ�ù��߷���
public class CrowdUtil {
	
	
	public static ResultEntity<String> uploadFileToOss(
			InputStream inputStream, 
			String originalName) {
		
		 String endpoint="http://oss-cn-shenzhen.aliyuncs.com";
		String accessKeyId="LTAI4G3LXhvXMsV6zAQ6nuv8";
		String accessKeySecret="Dv7WErWYfwegoJaWhCfOffLP8xEfJZ";
		String bucketName="ljw1998";
		String bucketDomain="http://ljw1998.oss-cn-shenzhen.aliyuncs.com";
		
		// ���� OSSClient ʵ����
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// �����ϴ��ļ���Ŀ¼
		String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
		 
		// �����ϴ��ļ��� OSS �������ϱ���ʱ���ļ���
		// ԭʼ�ļ�����beautfulgirl.jpg
		// �����ļ�����wer234234efwer235346457dfswet346235.jpg
		// ʹ�� UUID �����ļ���������
		String fileMainName = UUID.randomUUID().toString().replace("-", "");

		// ��ԭʼ�ļ����л�ȡ�ļ���չ��
		String extensionName = originalName.substring(originalName.lastIndexOf("."));

		// ʹ��Ŀ¼���ļ��������ơ��ļ���չ����ƴ�ӵõ���������
		String objectName = folderName + "/" + fileMainName + extensionName;

		try {
			// ���� OSS �ͻ��˶���ķ����ϴ��ļ�����ȡ��Ӧ�������
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

			// ����Ӧ����л�ȡ������Ӧ��Ϣ
			ResponseMessage responseMessage = putObjectResult.getResponse();

			// ������Ӧ״̬���ж������Ƿ�ɹ�
			if (responseMessage == null) {
				// ƴ�ӷ��ʸո��ϴ����ļ���·��
				String ossFileAccessPath = bucketDomain + "/" + objectName;

				// ��ǰ�������سɹ�
				return ResultEntity.successWithData(ossFileAccessPath);

			} else {
				// ��ȡ��Ӧ״̬��
				int statusCode = responseMessage.getStatusCode();

				// �������û�гɹ�����ȡ������Ϣ
				String errorMessage = responseMessage.getErrorResponseAsString();

				// ��ǰ��������ʧ��
				return ResultEntity.failed(" �� ǰ �� Ӧ ״ ̬ �� =" + statusCode + " �� �� �� Ϣ=" + errorMessage);
			}
		} catch (Exception e) {

			e.printStackTrace();
			// ��ǰ��������ʧ��
			return ResultEntity.failed(e.getMessage());
		} finally {
			if (ossClient != null) {
				// �ر� OSSClient��
				ossClient.shutdown();
			}
		}
	}
	
	
	
	// 1.�ж��Ƿ�Ϊajax����
	public static boolean ajaxRequestType(HttpServletRequest request) {
           // 1.��ȡ����ͷ
		String header = request.getHeader("Accept");
		String xreheader = request.getHeader("X-Requested-With");
		// 2.�ж�
		return (header!=null &&header.contains("application/json"))||(xreheader!=null&&xreheader.equals("XMLHttpRequest"));
	}
	
	// 2.����
     public static String md5(String source) {
    	 // �ж�
    	 if (source==null||source.length()==0) {
			throw new RuntimeException(CrowdConstand.MESSAGE_INVALI);
		}
    	 
    	 // ��ȡMessageDigest
    	 try {
    		 String AL="md5";
			MessageDigest instance = MessageDigest.getInstance(AL);
			// ��ȡ�����ַ�����Ӧ���ַ�����
			byte[] bytes = source.getBytes();
			// ִ�м���
			byte[] digest = instance.digest(bytes);
			// ����BigInteger
			BigInteger bigInteger = new BigInteger(1,digest);
			// ����ʮ������ת��
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
