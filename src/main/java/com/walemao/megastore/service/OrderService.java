package com.walemao.megastore.service;

import java.util.Date;

import com.walemao.megastore.domain.CurrentPage;
import com.walemao.megastore.domain.Order;

public interface OrderService {
	public CurrentPage<Order> getAllOrders(String parm, Date startTime,
			Date endTime, int orderStatus, int mark);
	public CurrentPage<Order> getAllOrders(Date startTime, Date endTime,int orderStatus,
			String username);
}
