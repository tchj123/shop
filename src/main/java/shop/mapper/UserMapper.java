package shop.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import shop.bean.user.User;
import shop.bean.user.UserInfo;

@Mapper
public interface UserMapper {
	Optional<User> getUser(String username);
	int addUser(UserInfo user);
	int changePassword(User user);
}
