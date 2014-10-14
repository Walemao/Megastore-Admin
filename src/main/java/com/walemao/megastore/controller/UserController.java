package com.walemao.megastore.controller;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walemao.megastore.domain.CurrentPage;
import com.walemao.megastore.domain.User;
import com.walemao.megastore.service.UserService;
import com.walemao.megastore.util.DateUtil;

@Controller
public class UserController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService UserService;

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage() {
		return "index";
	}

	/**
	 * 查看用户列表
	 * 
	 * */
	@RequestMapping(value = "/admin/users", method = { RequestMethod.GET })
	public String getUsers(
			CurrentPage<User> currentPage,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date endDate,
			@RequestParam(defaultValue = "1") int enabled,
			HttpServletRequest request) {

		if (startDate == null || endDate == null) {
			endDate = new Date(currentDate.getTime() + INTERVAL_TIME);
			startDate = new Date(currentDate.getTime() - 7 * INTERVAL_TIME);
		}

		CurrentPage<User> cp = this.UserService.getUsers(username, startDate,
				new Date(endDate.getTime() + INTERVAL_TIME), enabled);
		logger.debug("打印对象：{}", cp.getPageItems());
		request.setAttribute("username", username);
		request.setAttribute("curretPage", cp);
		request.setAttribute("enabled", enabled);
		request.setAttribute("startDate", DateUtil.FormatToF(startDate));
		request.setAttribute("endDate", DateUtil.FormatToF(endDate));
		return "admin/user/users";
	}

	/**
	 * 查看用户信息
	 * 
	 * */
	@RequestMapping(value = "/admin/user/{id}", method = { RequestMethod.GET })
	public String getUser(@PathVariable("id") int userId,
			HttpServletRequest request) {
		User user = this.UserService.getUser(userId);
		
		request.setAttribute("user", user);
		request.setAttribute("startDate", DateUtil.getDefaultDates().get(0));
		request.setAttribute("endDate", DateUtil.getDefaultDates().get(1));
		return "admin/user/user";
	}

	/**
	 * 禁用用户
	 * 
	 * */
	@RequestMapping(value = "/admin/user", method = { RequestMethod.DELETE })
	public @ResponseBody Map<String, Object> deleteUser(String username,
			boolean enabled, HttpServletRequest request) {
		Map<String, Object> requestMap = new HashMap<String, Object>();

		try {
			this.UserService.delete(username, enabled);
			requestMap.put("status", "success");
			return requestMap;

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		requestMap.put("status", "danger");
		return requestMap;
	}

}
