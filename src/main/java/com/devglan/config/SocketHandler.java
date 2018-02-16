package com.devglan.config;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {
	
	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
		/*for(WebSocketSession webSocketSession : sessions) {
			webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
		}*/
			session.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
			
			System.out.println("getHandshakeHeaders: " + session.getHandshakeHeaders());

			
		    MultiValueMap<String, String> map = UriComponentsBuilder.fromUri(session.getUri()).build().getQueryParams();
		    System.out.println(map.toString());

			
			System.out.println("LocalAddress: " + session.getLocalAddress());
			System.out.println("RemoteAddress: " + session.getRemoteAddress());
			//System.out.println("Principal.getName(): " + session.getPrincipal().getName());
			
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//the messages will be broadcasted to all users.
		sessions.add(session);
	}

}