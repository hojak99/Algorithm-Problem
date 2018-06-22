package com.kjh.hojak.reverse;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/reverse")
public class ReverseWebSocketEndpoint {

    @OnMessage
    public void hnaldeMessage(Session session, String message) throws IOException {
        session.getBasicRemote()
                .sendText("Reversed : "+new StringBuilder(message).reverse());
    }
}
