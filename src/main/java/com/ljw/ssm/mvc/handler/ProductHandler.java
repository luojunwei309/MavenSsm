package com.ljw.ssm.mvc.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Product;
import com.ljw.ssm.service.api.ProductService;
import com.ljw.ssm.util.CrowdUtil;
import com.ljw.ssm.util.ResultEntity;

@Controller
public class ProductHandler {
	
	private Logger loger=LoggerFactory.getLogger(ProductHandler.class);
	
	@Autowired
	private ProductService productService;
	
	// 查询某个分类下的所有分类并分页
	@RequestMapping("/admin/ProductGet.html")
	public String productGetAllPage(@RequestParam("id")Integer id,
			@RequestParam(value = "keyword",defaultValue = "")String keyword,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
			Model model) {
		
		PageInfo<Product> pageInfo=productService.productAllPageGet(id,keyword,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("id", id);
		return "admin-product";
	}
	
	// 跳转到添加页面
	@RequestMapping("/admin/productToAdd.html")
	public String productToAdd(@RequestParam("cid")Integer cid,Model model) {
		model.addAttribute("cid", cid);
		return "admin-productAdd";
	}
	
	// 添加产品
	@RequestMapping("/admin/productAdd.html")
	public String productAdd(
			@RequestParam("name")String name,@RequestParam("subTitle")String subTitle,
			@RequestParam("originalPrice")Float originalPrice,@RequestParam("promotePrice")Float promotePrice,
			@RequestParam("stock")Integer stock,@RequestParam("cid")Integer cid,
			@RequestParam("returnPicture")MultipartFile multipartFile) throws IOException {
		
		ResultEntity<String> entity = CrowdUtil.uploadFileToOss(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		String pictname=entity.getData();
		if (ResultEntity.SUCCESS.equals(entity.getResult())) {
			productService.save(name,subTitle,originalPrice,promotePrice,stock,cid,pictname);
		}
		
		return "redirect:/admin/ProductGet.html?id="+cid;
	}
	
	// 删除产品
	@RequestMapping("/admin/productDel.html")
	public String productDel(@RequestParam("iid")Integer iid,@RequestParam("id")Integer id) {
		
		productService.delProduct(iid);
		
		return "redirect:/admin/ProductGet.html?id="+id;
	}
	
	// 去修改页面
		@RequestMapping("/admin/productToUpdate.html")
		public String productToUpdate(@RequestParam("iid")Integer iid,@RequestParam("id")Integer id,
				Model model) {
			
			Product product=productService.selectProduct(iid);
			
			model.addAttribute("product", product);
			model.addAttribute("cid", id);
			loger.debug(product.toString());
			return "admin-productUpdate";
		}
	
		// 修改产品
		@RequestMapping("/admin/productUpdate.html")
		public String productUpdate(@RequestParam("id") Integer id,
				@RequestParam("name")String name,@RequestParam("subTitle")String subTitle,
				@RequestParam("originalPrice")Float originalPrice,@RequestParam("promotePrice")Float promotePrice,
				@RequestParam("stock")Integer stock,@RequestParam("cid")Integer cid,
				@RequestParam(value = "returnPicture",required = false )MultipartFile multipartFile) throws IOException {
			
			ResultEntity<String> entity = CrowdUtil.uploadFileToOss(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
			String pictname=entity.getData();
			if (ResultEntity.SUCCESS.equals(entity.getResult())) {
				productService.update(name,subTitle,originalPrice,promotePrice,stock,cid,pictname,id);
			}
			
			return "redirect:/admin/ProductGet.html?id="+cid;

}
}
