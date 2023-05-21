package br.inatel.dm110.product.beans.auditing;

import br.inatel.dm110.api.auditing.AuditingTO;
import br.inatel.dm110.interfaces.auditing.AuditingLocal;
import br.inatel.dm110.interfaces.auditing.AuditingRemote;
import br.inatel.dm110.product.entities.auditing.Auditing;
import br.inatel.dm110.product.interfaces.mappers.AuditingMapper;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
@Local(AuditingLocal.class)
@Remote(AuditingRemote.class) // change to Remote if necessary
public class AuditingBean implements AuditingLocal, AuditingRemote {

	private static final Logger log = Logger.getLogger(AuditingBean.class.getName());

	@PersistenceContext(unitName = "product_pu")
	private EntityManager em;

	@Inject
	private AuditingMapper auditingMapper;

	private final static String QUERY_PARAMETERS = "from Auditing a";

	public AuditingBean(){}

	@Override
	public List<AuditingTO> getAuditingByProductCode(String productCode){
		log.info("EJB -> Retrieving product auditing by product code: " + productCode);
		String queryParameter = " where a.registerCode like ?1";
		return toCollectionAPIModel(executeAuditingQuery(queryParameter).setParameter(1,productCode).getResultList());
	}

	@Override
	public List<AuditingTO> listAllAuditing() {
		log.info("EJB -> Retrieving all auditing!");
		return toCollectionAPIModel(executeAuditingQuery("").getResultList());
	}

	@Override
	public void createAuditing(AuditingTO auditingTO) {
		Auditing auditingEntity = auditingMapper.auditingDtoToAuditing(auditingTO);
		log.info("EJB -> Logging auditing " + auditingEntity);
		em.persist(auditingEntity);
	}

	private List<AuditingTO> toCollectionAPIModel(List<Auditing> auditingList) {
		return auditingList.stream().map(a -> auditingMapper.auditingToAuditingDto(a)).collect(Collectors.toList());
	}

	private TypedQuery<Auditing> executeAuditingQuery(String parameter){
		return em.createQuery(QUERY_PARAMETERS + parameter, Auditing.class);
	}


}