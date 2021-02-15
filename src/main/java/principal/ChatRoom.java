package principal;


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@ServerEndpoint("/html/ChatRoom")
public class ChatRoom {

    static Set<Session> chatRoomUsers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void handleOpen(Session userSession) {
        chatRoomUsers.add(userSession);
    }

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {

        String userName = (String) userSession.getUserProperties().get("userName");
        if (userName == null) {
            userSession.getUserProperties().put("userName", message);
            userSession.getBasicRemote().sendText(buildJsonData("System ", "Estás conectado como " + message));
        } else {
            Iterator<Session> iterator = chatRoomUsers.iterator();
            while (iterator.hasNext()) iterator.next().getBasicRemote().sendText(buildJsonData(userName, message));
        }

    }

    @OnClose
    public void handleClose(Session userSession) {
        chatRoomUsers.remove(userSession);
    }

    private String buildJsonData(String userName, String message) {
        JsonObject jsonObject = Json.createObjectBuilder().add("message", userName + ": " + message).build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {jsonWriter.write(jsonObject);}

        return stringWriter.toString();
    }

}
