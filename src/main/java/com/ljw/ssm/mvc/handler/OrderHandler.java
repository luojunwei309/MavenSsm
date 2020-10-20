package com.ljw.ssm.mvc.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.OrderItem;
import com.ljw.ssm.service.api.OrderService;

@Controller
public class OrderHandler {
	
	private Logger logger=LoggerFactory.getLogger(OrderHandler.class);
	
	@Autowired
	private OrderService orderService;
	
	// 查询全部订单并分页
	@RequestMapping("/admin/orderGet.html")
	public String orderGetAllPage(
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,Model model) {
		
		PageInfo<OrderItem> pageInfo=orderService.orderAllGetPage(pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "admin-order";
	}
	
	// 发货
	@RequestMapping("/order/fahuo.html")
	public String fahuo(@RequestParam("oid")Integer oid,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum) {
		orderService.updateOrder(oid);
		return "redirect:/admin/orderGet.html?pageNum="+pageNum;
	}

}
