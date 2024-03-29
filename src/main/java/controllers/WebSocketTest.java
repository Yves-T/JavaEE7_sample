package controllers;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * User: Yves-T
 * Date: 11/11/13
 * Time: 09:36
 */
@ServerEndpoint("/websocket")
public class WebSocketTest {

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        // print the client message for testing purposes
        System.out.println("Received: " + message);

        // send the first message to the client
        session.getBasicRemote().sendText("This is the server message");

        // send 3 messages to the client every 5 seconds
        int sentMessages = 0;
        while (sentMessages < 3) {
            Thread.sleep(5000);
            session.getBasicRemote().sendText("This is an intermediate server message. Count: " + sentMessages);
            sentMessages++;
        }

        // send a final message to the client
        session.getBasicRemote().sendText("This is the last server message");
    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }
}
