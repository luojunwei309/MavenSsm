package com.ljw.ssm.mvc.handler;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Category;
import com.ljw.ssm.exception.aaa;
import com.ljw.ssm.service.api.CategoryService;
import com.ljw.ssm.util.CrowdUtil;
import com.ljw.ssm.util.ResultEntity;

@Controller
public class CategoryHandler {
	
	private Logger logger=LoggerFactory.getLogger(CategoryHandler.class);
	
	@Autowired
	private CategoryService categoryService;
	
	
	// 查询分类，并整理
	@RequestMapping("/admin/categoryGet.html")
	public String adminCategory(@RequestParam(value = "keyword",defaultValue = "") String keyword,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
			Model model) {
		PageInfo<Category> pageInfo=categoryService.getPageInfo(keyword,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin-category";
	}
	
	
	// 添加分类
	@RequestMapping("/admin/categoryAdd.html")
	public String adminCategory(@RequestParam("returnPicture")MultipartFile multipartFile,
			@RequestParam("name") String name,
			@RequestParam(value = "keyword",defaultValue = "") String keyword,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum) throws IOException {
		
		ResultEntity<String> entity = CrowdUtil.uploadFileToOss(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		String pictname = entity.getData();
		if (entity.getResult().equals(ResultEntity.SUCCESS)) {
			categoryService.saveCategory(pictname,name);
			if (logger.isDebugEnabled()) {
				logger.debug("保存成功:"+pictname+";"+name);
			}
			return "redirect:/admin/categoryGet.html";
		}else {
			return "";
		}
	}
	
	// 删除单条分类
	@RequestMapping("/admin/categoryDel.html")
	public String adminCategoryDel(@RequestParam("id")Integer id) throws Exception {
		
		String del="删除成功"+id;
		try {
			categoryService.Del(id);
			
			logger.debug(del.toString());
			
			return "redirect:/admin/categoryGet.html";

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
	
	// 跳转到要修改的页面
	@RequestMapping("/admin/categoryToUpdate.html")
	public String adminCategoryUpdate(@RequestParam("id") Integer id,Model model) {
		Category category=categoryService.selectCategory(id);
		model.addAttribute("category", category);
		return "admin-categoryUpdate";
	}
	
	// 修改category
	@RequestMapping("/admin/categoryUpdate.html")
	public String adminCategoryUpdate(@RequestParam("id")Integer id,
			@RequestParam("name") String name,
			@RequestParam("returnPicture")MultipartFile multipartFile) throws IOException {
		if (name == null) {
			throw new aaa("name 属性为空，修改category失败");
		}
		ResultEntity<String> entity = null;
		if (multipartFile != null) {
			entity = CrowdUtil.uploadFileToOss(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		}
		String pictname = entity.getData();

		
		categoryService.update(id,name,pictname);

		return "redirect:/admin/categoryGet.html";

	}
	
	// 删除多个
	@RequestMapping("/admin/cateDelAll.html")
	public String allDel(@RequestParam("id") String[] id) {

		
		for (String string : id) {
			logger.debug(string);
			int parseInt = Integer.parseInt(string);
			categoryService.remove(parseInt);
			logger.debug("删除成功"+parseInt);
		}
		
		//int[] array=Arrays.asList(id).stream().mapToInt(Integer::parseInt).toArray();
		
		
		
		return "redirect:/admin/categoryGet.html";
	}

}
