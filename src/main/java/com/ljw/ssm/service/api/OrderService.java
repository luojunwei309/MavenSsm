package com.ljw.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.ljw.ssm.entity.Order;
import com.ljw.ssm.entity.OrderItem;

public interface OrderService {

	// ��ѯȫ����������ҳ
	PageInfo<OrderItem> orderAllGetPage(Integer pageNum, Integer pageSize);

	// ����
	void updateOrder(Integer oid);

}
