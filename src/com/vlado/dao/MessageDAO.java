package com.vlado.dao;

import java.util.List;

import com.vlado.entity.Message;

public interface MessageDAO {
	
	List<Message> getMessages();
	
	Message getMessageById(int id);
	
	void saveMessage(Message message);
	
	void deleteMessage(int id);
	
	void toggleMessage(int id);
}
