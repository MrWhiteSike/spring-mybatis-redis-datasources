package com.bsk.controller.ssmone;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.bsk.entity.Content;
import com.bsk.service.ContentService;

/**
 * 该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket
 * 类似Servlet的注解mapping。无需再web.xml中配置。
 * configurator = SpringConfigurator.class 是为了使该类可以通过Spring注入。
 * @author Lenovo
 *
 */
@ServerEndpoint(value = "/websocket", configurator = SpringConfigurator.class)
public class MyWebSocket {
	// 静态变量，用来记录当前在线连接数。把它设计成线程安全的
	private static int onlineCount = 0;
	@Autowired
	private ContentService contentService;
	/**
	 * concurrent 包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
	 * 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	 */
	private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
	// 与客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	
	public MyWebSocket() {}
	
	/**
	 * 连接建立成功调用方法
	 * @param session 可选参数，session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);     // 加入set中
		addOnlineCount();           // 在线数加一
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}
	
	
	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);    // 从set中删除
		subOnlineCount();             // 在线数减一
		System.out.println("有一条连接关闭！当前在线人数为" + getOnlineCount());
	}
	
	/**
	 * 收到客户端消息后调用的方法
	 * @param message	客户端发送过来的消息
	 * @param session	可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("来自客户端的消息：" + message);
		
		for (MyWebSocket myWebSocket : webSocketSet) {
			
			try {
				myWebSocket.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			
		}
	}
	
	/**
	 * 发生错误时调用的方法
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}
	
	/**
	 * 根据需要，保存数据的内部方法
	 * @param message
	 * @throws IOException
	 */
	private void sendMessage(String message) throws IOException{
		// 保存数据到数据库
		Content content = new Content();
		content.setContent(message);
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		content.setCreatedate(sm.format(new Date()));
		contentService.insertSelective(content);
		this.session.getBasicRemote().sendText(message);
		
	}

	private static synchronized void subOnlineCount() {
		MyWebSocket.onlineCount--;
	}

	private static synchronized int getOnlineCount() {
		return onlineCount;
	}

	private static synchronized void addOnlineCount() {
		MyWebSocket.onlineCount++;
	}
	
	
	
	
}
