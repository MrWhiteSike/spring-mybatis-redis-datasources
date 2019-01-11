package com.bsk.test;

import org.junit.Test;

import com.bsk.entity.Content;
import com.bsk.entity.ContentLog;
import com.bsk.util.CommonUtil;

public class ReflectTest {
	@Test
	public void insertContentLog() {
		Content content = new Content();
		content.setContent("jafewoijf");
		content.setContentname("jafowe");
		content.setCreatedate("2019-01-07");
		
		
		ContentLog log = new ContentLog();
		CommonUtil.setLogValueModelToModel(content, log);
		
		System.out.println(log);
		
	}
}
