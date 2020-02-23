package shop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.bean.user.Address;
import shop.bean.user.RegisterState;
import shop.bean.user.User;
import shop.bean.user.UserInfo;
import shop.mapper.AddressMapper;
import shop.mapper.UserMapper;
import shop.service.UserService;
import shop.util.ObjectAnalyzer;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	AddressMapper addrMapper;

	public int login(User user) {
		User userDB=userMapper.getUser(user.getUsername()).orElse(null);
		if(userDB!=null && userDB.getPassword().equals(user.getPassword()))
			return userDB.getUserId();
		return -1;
	}

	public RegisterState register(UserInfo user) {

		//检验用户名是否存在
		User userDB=userMapper.getUser(user.getUsername()).orElse(new User());
		if(user.getUsername().equals(userDB.getUsername()))
			return RegisterState.USERNAME_REPEATED;

		//添加用户
		if(userMapper.addUser(user)==1)
			return RegisterState.SUCCESS;

		return RegisterState.FAIL;
	}

	@Override
	public String changePassword(User user) {
		return userMapper.changePassword(user)==1?"success":"fail";
	}

	@Override
	public String changeUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return "fail";
	}

	@Override
	public Address getAddress(int userId, int addrId) {
		return addrMapper.getAddress(userId,addrId).orElse(new Address());
	}

	@Override
	public List<Address> getAddressList(int userId) {
		return addrMapper.getAddressList(userId);
	}

	@Override
	public String addAddress(Address address) {
		return null;
	}

	@Override
	public String saveAddress(Address address) {
		return null;
	}


}
