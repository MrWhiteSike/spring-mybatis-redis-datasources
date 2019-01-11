package com.bsk.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bsk.dao.ContentDao;
import com.bsk.entity.Content;
import com.bsk.mapper.ContentMapper;
@Repository
public class ContentDaoImpl implements ContentDao{
	
	@Resource
	private ContentMapper contentMapper;

	@Override
	public List<Content> findContentList() {
		return contentMapper.findContentList();
	}

	@Override
	public int insertSelective(Content content) {
		return contentMapper.insertSelective(content);
	}

}
