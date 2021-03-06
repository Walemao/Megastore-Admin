package com.walemao.megastore.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walemao.megastore.domain.CurrentPage;
import com.walemao.megastore.domain.User;
import com.walemao.megastore.domain.UserBase;
import com.walemao.megastore.domain.UserDetail;
import com.walemao.megastore.repository.UserBaseDao;
import com.walemao.megastore.repository.UserDao;
import com.walemao.megastore.repository.UserDetailDao;
import com.walemao.megastore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserBaseDao userBaseDao;
	@Autowired
	private UserDetailDao userDetailDao;

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

	@Override
	public CurrentPage<User> getUsers(String username, Date startTime,
			Date endTime, int enabled) {
		// TODO Auto-generated method stub
		return userDao.getUsers(username, startTime, endTime, enabled);
	}

	@Override
	public void delete(String username, boolean enabled) {
		// TODO Auto-generated method stub
		userDao.delete(username, enabled);
	}

	@Override
	public User getUser(int userid) {
		// TODO Auto-generated method stub
		User user = userDao.getUser(userid);
		UserBase userbase = userBaseDao.getUserBase(user.getUsername());
		UserDetail userdetail = userDetailDao.getUserDetail(user.getUsername());
		user.setUserbase(userbase);
		user.setUserdetail(userdetail);
		return user;
	}

}
