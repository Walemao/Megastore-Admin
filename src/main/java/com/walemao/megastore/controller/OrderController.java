package com.walemao.megastore.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walemao.megastore.domain.CurrentPage;
import com.walemao.megastore.domain.Order;
import com.walemao.megastore.service.OrderService;
import com.walemao.megastore.util.DateUtil;

@Controller
public class OrderController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	/**
	 * 查看订单列表
	 * 
	 * */
	@RequestMapping(value = "/admin/orders", method = { RequestMethod.GET })
	public String getOrders(
			CurrentPage<Order> currentPage,
			@RequestParam(required = false) String parm,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date endDate,
			@RequestParam(defaultValue = "-1") int orderStatus,
			@RequestParam(defaultValue = "0") int mark,
			HttpServletRequest request) {

		if (startDate == null || endDate == null) {
			endDate = new Date(currentDate.getTime() + INTERVAL_TIME);
			startDate = new Date(currentDate.getTime() - 7 * INTERVAL_TIME);
		}
		CurrentPage<Order> cp = this.orderService.getAllOrders(parm, startDate,
				endDate, orderStatus, mark);
		logger.debug("打印对象：{}", cp.getPageItems());
		request.setAttribute("curretPage", cp);
		request.setAttribute("startDate", DateUtil.FormatToF(startDate));
		request.setAttribute("endDate", DateUtil.FormatToF(endDate));
		return "admin/order/orders";
	}

	/**
	 * 根据用户查看订单
	 * 
	 * */
	@RequestMapping(value = "/admin/user/orders", method = RequestMethod.GET)
	public @ResponseBody List<Order> getOrdersByUsername(
			String username,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date endDate,
			@RequestParam(defaultValue = "-1") int orderStatus,
			HttpServletRequest request) {

		return this.orderService.getAllOrders(startDate,
				new Date(endDate.getTime() + INTERVAL_TIME), orderStatus,
				username);
	}
}
