package shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import shop.bean.user.Address;
import shop.bean.user.RegisterState;
import shop.bean.user.User;
import shop.bean.user.UserInfo;
import shop.service.UserService;
import shop.util.ObjectAnalyzer;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/login")
	public String login(User user,HttpSession session) {

		int userId=userService.login(user);
		if(userId!=-1) {
			user.setUserId(userId);
			session.setAttribute("user", user);
			return "success";
		}
		return "fail";
			
	}
	@RequestMapping("/register")
	public RegisterState register(UserInfo user) {
		return userService.register(user);
	}

	@RequestMapping("/log")
	public String loginPage(){
		System.out.println("ha");
		return "product";
	}
	@RequestMapping("/changePassword")
	public String changePassword(HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(user==null)
			throw new RuntimeException("session中不存在user记录");
		return userService.changePassword(user);
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "success";
	}

	@RequestMapping(value = {"/index","/",""})
	public ModelAndView index(HttpSession session)
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("index");
		User user= (User) session.getAttribute("user");
		if(user!=null)
			mav.addObject("username",user.getUsername());
		return mav;

	}

	@RequestMapping("/saveAddress")
	public String saveAddress(Address address){
		return "success";
	}

	@RequestMapping("/addAddress")
	public String addAddress(Address address){
		return "success";
	}


}
