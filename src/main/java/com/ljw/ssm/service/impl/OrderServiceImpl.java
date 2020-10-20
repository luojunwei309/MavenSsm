package com.ljw.ssm.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.format.DataFormatDetector;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Order;
import com.ljw.ssm.entity.OrderItem;
import com.ljw.ssm.mapper.OrderItemMapper;
import com.ljw.ssm.mapper.OrderMapper;
import com.ljw.ssm.service.api.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	private Logger logger=LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper ;
	
	// 查询全部订单并分页
	@Override
	public PageInfo<OrderItem> orderAllGetPage( Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<OrderItem> list=orderItemMapper.selectOderAll();
		System.out.println(list);
		for (OrderItem orderItem : list) {
//			Date createDate = orderItem.getOrder().getCreateDate();
//			Date confirmDate = orderItem.getOrder().getConfirmDate();
//			Date payDate = orderItem.getOrder().getPayDate();
//			Date deliveryDate = orderItem.getOrder().getDeliveryDate();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		    String format2 = format.format(createDate);
//		    String format3 = format.format(confirmDate);
//		    String format4 = format.format(payDate);
//		    String format5 = format.format(deliveryDate);
		    String status = orderItem.getOrder().getStatus();
		    switch (status) {
			case "1":
				orderItem.getOrder().setStatus("waitPay");
				break;
			case "2":
				orderItem.getOrder().setStatus("waitDelivery");
				break;
			case "3":
				orderItem.getOrder().setStatus("waitConfirm");
				break;
			case "4":
				orderItem.getOrder().setStatus("waitReview");
				break;
			case "5":
				orderItem.getOrder().setStatus("toCar");
				break;
			default:
				break;
			}
//		    	try {
//					Date valueOf = java.sql.Date.valueOf(format2);
//					Date valueOf1 = java.sql.Date.valueOf(format3);
//					Date valueOf2 = java.sql.Date.valueOf(format4);
//					Date valueOf3 = java.sql.Date.valueOf(format5);
//					orderItem.getOrder().setCreateDate(valueOf);
//					orderItem.getOrder().setConfirmDate(valueOf1);
//					orderItem.getOrder().setPayDate(valueOf2);
//					orderItem.getOrder().setDeliveryDate(valueOf3);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		}
		return new PageInfo<OrderItem>(list);
	}

	
	// 发货
	@Override
	public void updateOrder(Integer oid) {
		Date date = new Date();
		orderMapper.updateOrderStatus(oid,date);
	}

	

}
