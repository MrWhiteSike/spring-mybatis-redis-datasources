package com.bsk.cxf.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.bsk.cxf.HelloWorld;
@Component("helloWorld")
@WebService
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String say(String string) {
		return "Hello" + string;
	}

}
