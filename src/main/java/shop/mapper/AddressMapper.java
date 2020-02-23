package shop.mapper;

import java.util.List;
import java.util.Optional;

import shop.bean.user.Address;

public interface AddressMapper {
	Optional<Address> getAddress(int userId,int addrId);
	List<Address> getAddressList(int userId);
}
