package com.walemao.megastore.service;


import java.util.Date;

import com.walemao.megastore.domain.CurrentPage;
import com.walemao.megastore.domain.User;

public interface UserService {
	public CurrentPage<User> getUsers(String username, Date startTime,
			Date endTime, int enabled);
	public User getUser(int userid);
	public int insert(User user);
	public void delete(String username, boolean enabled);
}
