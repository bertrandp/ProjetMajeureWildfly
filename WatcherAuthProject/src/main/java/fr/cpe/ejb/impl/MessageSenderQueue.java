package fr.cpe.ejb.impl;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import fr.cpe.ejb.MessageSenderQueueLocal;
import fr.cpe.model.User;

public class MessageSenderQueue implements MessageSenderQueueLocal {

	@Inject
	JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/watcherqueue")
	Queue queue;
	
	@Override
	public void sendMessage(String message) {
		context.createProducer().send(queue, message);
	}
	
	@Override
	public void sendMessage(User user) {
		context.createProducer().send(queue, user);
	}
	
}
