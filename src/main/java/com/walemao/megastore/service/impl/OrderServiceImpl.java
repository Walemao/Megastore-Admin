package com.walemao.megastore.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walemao.megastore.domain.CurrentPage;
import com.walemao.megastore.domain.Order;
import com.walemao.megastore.repository.OrderDao;
import com.walemao.megastore.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public CurrentPage<Order> getAllOrders(String parm, Date startTime,
			Date endTime, int mark) {
		// TODO Auto-generated method stub
		return orderDao.getAllOrders(parm, startTime, endTime, mark);
	}

}
