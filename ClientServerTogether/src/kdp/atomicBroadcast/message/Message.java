package kdp.atomicBroadcast.message;

import java.io.Serializable;

public interface Message<T> extends Serializable{

	public abstract void setBody(T body);
	
	public abstract T getBody();
	
}
