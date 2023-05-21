package br.inatel.dm110.product.beans.product;

import br.inatel.dm110.product.entities.auditing.Auditing;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class ProductQueueSender {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/ProductQueue")
	private Queue queue;

	public void sendObjectMessage(Auditing auditing) {
		try {
			Connection conn = connectionFactory.createConnection();
			Session session = conn.createSession();
			MessageProducer msgProducer = session.createProducer(queue);
			ObjectMessage objectMessage = session.createObjectMessage(auditing);
			msgProducer.send(objectMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendTextMessage(String msg) {
		try {
			Connection conn = connectionFactory.createConnection();
			Session session = conn.createSession();
			MessageProducer msgProducer = session.createProducer(queue);
			TextMessage textMessage = session.createTextMessage(msg);
			msgProducer.send(textMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}



}
