package com.lichking.impl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lichking.itf.dao.IUserDAO;
import com.lichking.itf.service.IUserService;
import com.lichking.pojo.web.UserVO;

@Service("UserService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDAO userDAO;
	
	@Override
	public UserVO getUser(String username) {
		return this.userDAO.selectByPrimaryKey(username);
	}

}
