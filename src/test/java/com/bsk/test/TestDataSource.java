package com.bsk.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bsk.entity.Constants;
import com.bsk.entity.User;
import com.bsk.service.UserService;
import com.bsk.util.DataSourceHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestDataSource {
	@Autowired
	private UserService userService;
	
	@Test
	public void selectByPrimaryKey() throws Exception{
		
		DataSourceHolder.setDataSources(Constants.DATASOURCE_TWO);
		User user = userService.getUserById(10);
		System.out.println(user.toString());
	}
}
