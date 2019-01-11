package com.bsk.controller.ssmone;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 后台 Controller
 * @author Lenovo
 *
 */
import org.springframework.web.servlet.ModelAndView;

import com.bsk.entity.T_user;
import com.bsk.service.T_UserService;
@Controller
@RequestMapping("/")
public class T_UserController {
	@Resource
	private T_UserService tUserService;
	
	@RequestMapping("/loginAdmin")
	public ModelAndView login(T_user user) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
		try {
			subject.login(token);
			return mv;
		} catch (Exception e) {
			
			// 这里将异常打印关闭是因为如果登录失败的话会自动抛异常
//			e.printStackTrace();
			mv.addObject("error", "用户名或密码错误");
			mv.setViewName("login");
			return mv;
		}
		
	}
	
	@RequestMapping("/admin")
	public ModelAndView admin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin");
		return mv;
	}
	@RequestMapping("/student")
	public ModelAndView student() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin");
		return mv;
	}
	@RequestMapping("/teacher")
	public ModelAndView teacher() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin");
		return mv;
	}
	
	
	
}
