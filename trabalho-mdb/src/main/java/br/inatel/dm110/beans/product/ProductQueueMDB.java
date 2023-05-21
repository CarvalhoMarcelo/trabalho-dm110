package br.inatel.dm110.beans.product;

import br.inatel.dm110.interfaces.auditing.AuditingLocal;
import br.inatel.dm110.product.entities.auditing.Auditing;
import br.inatel.dm110.product.interfaces.mappers.AuditingMapper;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ProductQueue") })

public class ProductQueueMDB implements MessageListener {

	private static final Logger log = Logger.getLogger(ProductQueueMDB.class.getName());

	@EJB
	private AuditingLocal auditingLocalBean;

	@Inject
	private AuditingMapper auditingMapper;

	public void onMessage(Message message) {
		// process message
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				String text = txtMessage.getText();
				log.info("Mensagem recebida da fila: " + text);
			}
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				Auditing auditing = (Auditing) objectMessage.getObject();
				log.info("Mensagem recebida da fila: " + auditing);
				//create auditing register
				auditingLocalBean.createAuditing(auditingMapper.auditingToAuditingDto(auditing));
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
