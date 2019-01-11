package com.bsk.shiro;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.bsk.entity.T_user;
import com.bsk.service.T_UserService;
/**
 * Shiro 自定义域
 * @author Lenovo
 *
 */
public class MyRealm extends AuthorizingRealm{
	@Resource
	private T_UserService tUserService;
	
	/**
	 * 用于权限的认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		
		String username = principalCollection.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> rolename = tUserService.findRoles(username);
		Set<String> permissions = tUserService.findPermissions(username);
		info.setRoles(rolename);
		info.setStringPermissions(permissions);
		return info;
	}
	
	/**
	 * 首先执行这个登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户账号
		String username = token.getPrincipal().toString();
		T_user user = tUserService.findUserByUsername(username);
		if(user != null) {
			// 将查询到的用户账号和密码存放到authenticationInfo ，用于后面的权限判断。第三个参数随便放一个就行
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "a");
			return authenticationInfo;
		}else {
			return null;
		}
	}
	
}
