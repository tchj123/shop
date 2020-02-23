package shop.service;

import shop.bean.user.Address;
import shop.bean.user.RegisterState;
import shop.bean.user.User;
import shop.bean.user.UserInfo;

import java.util.List;

public interface UserService {

	//成功返回userId，失败返回-1
	public int login(User user);
	public RegisterState register(UserInfo user);
	public String changePassword(User user);
	public String changeUserInfo(UserInfo userInfo);
	public Address getAddress(int userId,int addressId);
	public List<Address> getAddressList(int userId);
    String addAddress(Address address);
	String saveAddress(Address address);
}
