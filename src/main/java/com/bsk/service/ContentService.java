package com.bsk.service;


import java.util.List;

import com.bsk.entity.Content;

/**
 * 后台登录Service
 */
public interface ContentService {
    /**
     * 获取内容list
     * @return
     */
    List<Content> findContentList();

    /**
     * 根据条件新增
     * @param content
     * @return
     */
    int insertSelective(Content content) ;
}
