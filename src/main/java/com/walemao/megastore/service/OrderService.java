package com.walemao.megastore.service;

import java.util.Date;
import java.util.List;

import com.walemao.megastore.domain.CurrentPage;
import com.walemao.megastore.domain.Order;


public interface OrderService {
	public CurrentPage<Order> getAllOrders(String parm, Date startTime,
			Date endTime, int orderStatus, int mark);
	public List<Order>  getAllOrders(Date startTime, Date endTime,int orderStatus,
			String username);
}
