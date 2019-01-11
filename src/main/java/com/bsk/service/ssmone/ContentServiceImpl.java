package com.bsk.service.ssmone;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bsk.dao.ContentDao;
import com.bsk.entity.Content;
import com.bsk.service.ContentService;

@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ContentServiceImpl implements ContentService {
	
	@Resource
	private ContentDao contentDao;

	@Override
	public List<Content> findContentList() {
		return contentDao.findContentList();
	}

	@Override
	public int insertSelective(Content content) {
		return contentDao.insertSelective(content);
	}

}
