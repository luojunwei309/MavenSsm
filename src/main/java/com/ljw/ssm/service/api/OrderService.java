package com.ljw.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Order;
import com.ljw.ssm.entity.OrderItem;

public interface OrderService {

	// 查询全部订单并分页
	PageInfo<OrderItem> orderAllGetPage(Integer pageNum, Integer pageSize);

	// 发货
	void updateOrder(Integer oid);

}
