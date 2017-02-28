package com.lhq.ssm2.service.impl;

import javax.annotation.Resource;

import com.lhq.ssm2.IDao.Tb_userMapper;
import com.lhq.ssm2.domain.Tb_user;
import com.lhq.ssm2.service.IUserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private Tb_userMapper tb_userMapper;
	@Override
	public Tb_user getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.tb_userMapper.selectByPrimaryKey(userId);
	}

}