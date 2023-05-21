package br.inatel.dm110.impl.auditing;

import br.inatel.dm110.api.auditing.AuditingService;
import br.inatel.dm110.api.auditing.AuditingTO;
import br.inatel.dm110.interfaces.auditing.AuditingLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
public class AuditingServiceImpl implements AuditingService {

	@EJB
	private AuditingLocal auditingLocalBean;

	@Override
	public Response getAuditingByProductCode(String productCode) {
		List<AuditingTO> auditingTOList = auditingLocalBean.getAuditingByProductCode(productCode);
		return Response.ok(auditingTOList).build();
	}

	@Override
	public List<AuditingTO> listAllAuditing() {
		return auditingLocalBean.listAllAuditing();
	}


}
