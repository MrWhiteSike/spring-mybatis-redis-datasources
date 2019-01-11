package com.bsk.controller.ssmone;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bsk.entity.Content;
import com.bsk.service.ContentService;
import com.bsk.util.CommonUtil;

import net.sf.json.JSONObject;

@Controller
public class MainController {
	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
    private ContentService contentService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/turnToWebSocketIndex")
    public ModelAndView turnToWebSocketIndex() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("websocket/websocket");
		return mv;
    }
	
	/**
     * 加载聊天记录
     *
     * @param response
     */
    @RequestMapping("/content_load")
    public void content_load(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject jo = new JSONObject();
            List<Content> list = contentService.findContentList();
            jo.put("contents", list);
            jsonObject = CommonUtil.parseJson("1", "操作成功", jo);
        } catch (Exception e) {
            logger.error("操作异常", e);
            CommonUtil.parseJson("2", "操作异常", "");
        }
        CommonUtil.responseBuildJson(response, jsonObject);
    }
}
