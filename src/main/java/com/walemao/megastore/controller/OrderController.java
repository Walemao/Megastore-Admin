package com.walemao.megastore.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.walemao.megastore.domain.CurrentPage;
import com.walemao.megastore.domain.Order;
import com.walemao.megastore.domain.User;
import com.walemao.megastore.service.OrderService;

@Controller
public class OrderController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 查看订单列表
	 * 
	 * */
	@RequestMapping(value = "/admin/orders", method = { RequestMethod.GET })
	public String getOrders(CurrentPage<Order> currentPage,
			@RequestParam(required = false) String parm,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date endDate,
			@RequestParam(defaultValue = "0") int mark,
			HttpServletRequest request) {

		CurrentPage<Order> cp = this.orderService.getAllOrders(parm, startDate, endDate, mark);
		logger.debug("打印对象：{}", cp.getPageItems());
		request.setAttribute("curretPage", cp);
		return "admin/order/orders";
	}
}
