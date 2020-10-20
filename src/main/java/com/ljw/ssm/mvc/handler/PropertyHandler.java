package com.ljw.ssm.mvc.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Property;
import com.ljw.ssm.entity.PropertyValue;
import com.ljw.ssm.service.api.PropertyService;

@Controller
public class PropertyHandler {
	
	private Logger logger=LoggerFactory.getLogger(PropertyHandler.class);
	@Autowired
	private PropertyService propertyService;
	
	// 属性分页查找
	@RequestMapping("/admin/PropertyGet.html")
	public String propertyGetPage(@RequestParam("cid")Integer cid,
			@RequestParam(value = "keyword",defaultValue = "") String keyword,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
			Model model) {
		
		PageInfo<Property> pageInfo=propertyService.getCid(cid,keyword,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("cid", cid);
		return "admin-property";
	}
	
	// 属性添加按钮
	@RequestMapping("/admin/ToPropertyAdd.html")
	public String ToPropertyAdd(@RequestParam("cid")Integer cid,ModelMap map) {
		map.addAttribute("cid", cid);
		return "admin-propertyAdd";
	}
	
	// 属性添加
	@RequestMapping("/admin/PropertyAdd.html")
	public String PropertyAdd(@RequestParam("name")String name,@RequestParam("cid")Integer cid) {
		
		propertyService.propertyAdd(cid,name);
		
		return "redirect:/admin/PropertyGet.html?cid="+cid+"&pageNum="+Integer.MAX_VALUE;
	}
	
	// 去属性值修改的页面
	@RequestMapping("/admin/ToPropertyUpdate.html")
	public String PropertyTUpdate(Model model,
			@RequestParam("id") Integer id) {
		Property property=propertyService.propertySelect(id);
		model.addAttribute("property", property);
		return "admin-propertyUpdate";
	}
	
	// 真正修改
	@RequestMapping("/admin/PropertyUpdate.html")
	public String PropertyUpdate(Property property) {
		int cid=property.getCid();
		logger.debug(property.toString());

		propertyService.update(property);
		
		return "redirect:/admin/PropertyGet.html?cid="+cid+"&pageNum="+Integer.MAX_VALUE;
	}
	
	// 删除属性
	@RequestMapping("/admin/propertyDel.html")
	public String PropertyDel(@RequestParam("id")Integer id,
			@RequestParam("cid") Integer cid) {
		
		propertyService.del(id);
		
		return "redirect:/admin/PropertyGet.html?cid="+cid;
	}
	
	// 查找属性值
	@RequestMapping("/admin/propertyValue.html")
	public String propertyValueGet(@RequestParam("pid")Integer pid,@RequestParam("cid")Integer cid,
			Model model) {
		
		List<Property> propertyName=propertyService.getPropertyName(cid);
		List<PropertyValue> propertyNamevalue=propertyService.propertyGet(pid);
		model.addAttribute("propertyName", propertyName);
		model.addAttribute("propertyNamevalue", propertyNamevalue);
		model.addAttribute("pid", pid);
		model.addAttribute("cid", cid);
		return "admin-productValue";
	}
	
	// 修改或者添加属性值
	@RequestMapping("/admin/PropertyADD/Edit.html")
	public String propertyValueAddAndEdit(@RequestParam("pid")Integer pid,@RequestParam("cid")Integer cid,
			@RequestParam("ptid")Integer ptid,@RequestParam("value")String value) {
		
		logger.debug("------"+pid+ptid+value);
		
		propertyService.AddAndEdit(pid,ptid,value);
		
		return "redirect:/admin/propertyValue.html?pid="+pid+"&cid="+cid;
	}
	
}
