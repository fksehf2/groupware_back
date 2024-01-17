package frame.futil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// WebSocket의 호스트 주소 설정
@ServerEndpoint("/websocket/{userIp}/{userGb}/{userId}")
public class Websocket {
	
	protected Logger log = LoggerFactory.getLogger(Websocket.class);
	//왜 static 으로 만들었을까?
    static HashMap<String, Session> messageUserList = new HashMap<String, Session>();
    public static final String [] WHITE_LIST   = {"C01999", "C01003", "C01004"};
	
	// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void handleOpen(Session session, @PathParam("userIp") String userIp, @PathParam("userGb") String userGb, @PathParam("userId") String userId) {
		log.info("*************************************************************");
		// 콘솔에 접속 로그를 출력한다.
		log.info("client is now connected...");
		log.info("userIp : " + userIp + ", userGb : " + userGb + ", userId : " + userId);
		// java7 = Arrays.asList(UPLOADABLE_FILE_EXTENSION).contains(extension1);
		// java8 = Arrays.stream(UPLOADABLE_FILE_EXTENSION).anyMatch(extension1::equals);
		if(Arrays.stream(WHITE_LIST).anyMatch(userGb::equals)) {
			messageUserList.put(userId, session);
		}
		log.info("*************************************************************");
	}
	
//	// WebSocket으로 메시지가 오면 요청되는 함수
//	@OnMessage
//	public String handleMessage(String message) {
//		log.info("*************************************************************");
//		// 메시지 내용을 콘솔에 출력한다.
//		log.info("receive from client : " + message);
//		// 에코 메시지를 작성한다.
//		String replymessage = "echo " + message;
//		// 에코 메시지를 콘솔에 출력한다.
//		log.info("send to client : "+replymessage);
//		// 에코 메시지를 브라우저에 보낸다.
//		log.info("*************************************************************");
//		return replymessage;
//	}
	
	// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void handleMessage(String message) {
		log.info("*************************************************************");
		// 메시지 내용을 콘솔에 출력한다.
		log.info("receive from client : " + message);
		// 에코 메시지를 작성한다.
		String replymessage = "echo " + message;
		// 에코 메시지를 콘솔에 출력한다.
		log.info("send to client : "+replymessage);
		// 에코 메시지를 브라우저에 보낸다.
		broadCast(message);
		log.info("*************************************************************");
	}
	
	// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void handleClose(Session session) {
		log.info("*************************************************************");
		// 콘솔에 접속 끊김 로그를 출력한다.
		log.info("client is now disconnected...");
		String val = session.getId();//종료한 sessionId 확인
        
        Set<String>keys =  messageUserList.keySet();
        for(String key : keys) {        
            if(val.equals(messageUserList.get(key).getId())) {
            	log.info("종료하려는 userId : "+key);
                messageUserList.remove(key, session);
                log.info("현재 접속자 : "+messageUserList.size());
                //broadCast(key+"님께서 나가셨습니다.");
            }
        }   
		log.info("*************************************************************");
	}
	
	// WebSocket과 브라우저 간에 통신 에러가 발생하면 요청되는 함수.
	@OnError
	public void handleError(Throwable t) {
		log.info("*************************************************************");
		// 콘솔에 에러를 표시한다.
		t.printStackTrace();
		log.info("*************************************************************");
	}
	
	//메시지 전체 전송
    public void broadCast(String text){
    	log.info("*************************************************************");
    	log.info("전달 대상 : "+messageUserList.size());
        Set<String>keys =  messageUserList.keySet();
        try {            
            for(String key : keys) {
                log.info("key : "+key);
                Session session = messageUserList.get(key);    
                session.getBasicRemote().sendText(text);
                System.out.println(session.getId() + "ID!!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	log.info("*************************************************************");
        }
    }
    
}
