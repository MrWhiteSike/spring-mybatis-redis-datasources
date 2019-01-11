package com.bsk.controller.ssmone;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bsk.entity.User;
import com.bsk.lucene.util.LuceneIndex;
import com.bsk.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private UserService mUserService;
	
	
	@RequestMapping("/add")
	public ModelAndView addUserIndex() throws Exception {
		 LuceneIndex luceneIndex = new LuceneIndex();
		 List<User> users = new ArrayList<User>();
		 users = mUserService.getAllUser();
		 for (User user2 : users) {
			if(user2.getUserId() >= 7) {
				luceneIndex.addIndex(user2);
			    logger.debug("添加Lucene索引的用户：" + user2);
			}
		 }
		 
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("queryResult");
	     return mv;
	}
	
	@RequestMapping("/q")
    public ModelAndView search(@RequestParam(value = "q", required = false, defaultValue = "") String q,
                         @RequestParam(value = "page", required = false, defaultValue = "1") String page) throws Exception {
		
        LuceneIndex luceneIndex = new LuceneIndex();
        logger.debug("搜索关键字：" + q);
        List<User> userList = luceneIndex.searchUser(q);
        /**
         * 关于查询之后的分页我采用的是每次分页发起的请求都是将所有的数据查询出来，
         * 具体是第几页再截取对应页数的数据，典型的拿空间换时间的做法，如果各位有什么
         * 高招欢迎受教。
         */
        ModelAndView mv = new ModelAndView();
        
        Integer toIndex = userList.size() >= Integer.parseInt(page) * 3 ? Integer.parseInt(page) * 3 : userList.size();
        List<User> newList = userList.subList((Integer.parseInt(page) - 1) * 3, toIndex);
//        model.addAttribute("userList", newList);
        mv.addObject("userList", newList);
        String s = this.genUpAndDownPageCode(Integer.parseInt(page), userList.size(), q, 3, "/spring-mybatis-redis-lucene");
//        model.addAttribute("pageHtml", s);
//        model.addAttribute("q", q);
//        model.addAttribute("resultTotal", userList.size());
//        model.addAttribute("pageTitle", "搜索关键字'" + q + "'结果页面");
        mv.addObject("pageHtml", s);
        mv.addObject("q", q);
        mv.addObject("resultTotal", userList.size());
        mv.addObject("pageTitle", "搜索关键字'" + q + "'结果页面");

        mv.setViewName("queryResult");
        return mv;
    }
	
	/**
     * 查询之后的分页 前端代码和后端耦合严重，不建议！
     *
     * @param page
     * @param totalNum
     * @param q
     * @param pageSize
     * @param projectContext
     * @return
     */
    private String genUpAndDownPageCode(int page, Integer totalNum, String q, Integer pageSize, String projectContext) {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        StringBuffer pageCode = new StringBuffer();
        if (totalPage == 0) {
            return "";
        } else {
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager' >");
            if (page > 1) {
                pageCode.append("<li><a href='" + projectContext + "/q?page=" + (page - 1) + "&q=" + q + "'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            if (page < totalPage) {
                pageCode.append("<li><a href='" + projectContext + "/q?page=" + (page + 1) + "&q=" + q + "'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("</ul>");
            pageCode.append("</nav>");
        }
        return pageCode.toString();
    }
	
	
	
}
