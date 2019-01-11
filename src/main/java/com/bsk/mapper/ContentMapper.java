package com.bsk.mapper;

import java.util.List;

import com.bsk.entity.Content;

public interface ContentMapper {
	/**
     * 返回所有内容
     * @return
     */
    List<Content> findContentList();

    /**
     * 新增
     * @param content
     * @return
     */
    int insertSelective(Content content) ;
}
