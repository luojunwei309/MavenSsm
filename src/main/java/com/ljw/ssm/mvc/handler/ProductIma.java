package com.ljw.ssm.mvc.handler;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ljw.ssm.entity.ProductImages;
import com.ljw.ssm.exception.aaa;
import com.ljw.ssm.service.api.ProductImaService;
import com.ljw.ssm.util.CrowdUtil;
import com.ljw.ssm.util.ResultEntity;

@Controller
public class ProductIma {
	
	private Logger logger =LoggerFactory.getLogger(ProductIma.class);
	
	@Autowired
	private ProductImaService productImaService;
	
	// 查找产品单个的图片
	@RequestMapping("/admin/productImage.html")
	public String productGetAllIma(@RequestParam("pid")Integer pid,Model model) {
		
		List<ProductIma> imas=productImaService.selectAllIma(pid);
		List<ProductImages> images=productImaService.selectAllImages(pid);
		model.addAttribute("imas", imas);
		model.addAttribute("images", images);
		model.addAttribute("pid", pid);
		return "admin-productIma";
	}
	
	// 添加产品单图
	 @RequestMapping("/admin/productImage/Add.html")
	 public String productImaAdd(@RequestParam("image")MultipartFile multipartFile,
			 @RequestParam("pid") Integer pid) throws IOException {
		 
		 if (multipartFile.getSize() == 0) {
			throw new aaa("不能为空");
		}
		 ResultEntity<String> entity = CrowdUtil.uploadFileToOss(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		 
		 if (ResultEntity.FAILED.equals(entity.getResult())) {
			throw new aaa("保存失败");
		}
		 
		 String type=entity.getData();
		 
		 try {
			 productImaService.saveIma(pid,type);
		} catch (Exception e) {
			
		}
		 
		 
		 return "redirect:/admin/productImage.html?pid="+pid;
	 }
	 
	 // 删除
	 @RequestMapping("/admin/productImage/delete.html")
	 public String imaDel(@RequestParam("id") Integer id,
			 @RequestParam("pid") Integer pid) {
		 
		 productImaService.imaDel(id);
		 
		 return "redirect:/admin/productImage.html?pid="+pid;
	 }
	 
	 //
	 @RequestMapping("/admin/productImages/add.html")
	 public String ImasAdd(@RequestParam("images")MultipartFile multipartFile,
			 @RequestParam("pid") Integer pid) throws IOException {
		 
		 if (multipartFile.getSize() == 0) {
			throw new aaa("图片不能为空");
		}
		 
		 ResultEntity<String> entity = CrowdUtil.uploadFileToOss(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		 
		 if (ResultEntity.FAILED.equals(entity.getResult())) {
			 throw new aaa("上传失败");
		}
		 
		 String type=entity.getData();
		 try {
			productImaService.saveImages(pid,type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return "redirect:/admin/productImage.html?pid="+pid;
	 }
	 
	 // 删除详情图片
	 @RequestMapping("/admin/productImages/delete.html")
	 public String imagesDel(@RequestParam("id")Integer id,@RequestParam("pid") Integer pid) {
		 
		 productImaService.imagesDel(id);
		 
		 return "redirect:/admin/productImage.html?pid="+pid;
	 }

}
