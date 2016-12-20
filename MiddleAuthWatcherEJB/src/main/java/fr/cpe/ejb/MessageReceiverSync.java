package fr.cpe.ejb;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

import fr.cpe.model.User;

@Stateless
public class MessageReceiverSync implements MessageReceiverSyncLocal {

	Logger log = Logger.getLogger(MessageReceiverSync.class.getName());
	
	@Inject
	JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/watcherqueue")
	Queue queue;
	
	@Override
	public User receiveMessage() {

		Message message = context.createConsumer(queue).receive();
		
		try {
			if (message instanceof TextMessage) {
				log.info("Topic: I received a TextMessage at " + new Date());
				TextMessage msg = (TextMessage) message;
				log.info("Message is : " + msg.getText());
			} else if (message instanceof ObjectMessage) {
				log.info("Topic: I received an ObjectMessage at " + new Date());
				ObjectMessage msg = (ObjectMessage) message;
				if (msg.getObject() instanceof User) {
					User user = (User) msg.getObject();
					log.info("User Details: ");
					log.info("login:" + user.getLogin());
					log.info("pwd:" + user.getPassword());
					
					return user;

				}
			} else {
				log.info("Not valid message for this Queue MDB");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
