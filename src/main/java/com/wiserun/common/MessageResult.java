package com.wiserun.common;

import java.io.Serializable;
import java.util.List;

public interface MessageResult extends Serializable {

	void setSuccess(boolean success);

	void setMessage(String message);

	void setMessages(List<String> messages);
	
	void setAttach(Object attach);
}
