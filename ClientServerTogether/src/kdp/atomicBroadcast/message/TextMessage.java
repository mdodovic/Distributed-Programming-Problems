package kdp.atomicBroadcast.message;

public class TextMessage implements Message<String> {

	private static final long serialVersionUID = 1L;
	private String messageText;
	
	@Override
	public void setBody(String body) {
		messageText = body;		
	}

	@Override
	public String getBody() {
		return messageText;
	}

}
