package com.chat.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

  private Logger logger = Logger.getLogger(WebSocketHandler.class.getName());

  List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    logger.info("Sending message: " + message.getPayload() + " to " + sessions.size() + " sessions.");
    for(WebSocketSession webSocketSession : sessions) {
      webSocketSession.sendMessage(message);
    }
  }


}