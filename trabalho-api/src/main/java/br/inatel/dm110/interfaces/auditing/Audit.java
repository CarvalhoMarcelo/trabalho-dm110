package br.inatel.dm110.interfaces.auditing;

import br.inatel.dm110.api.auditing.AuditingTO;

import java.util.List;

public interface Audit {

	void createAuditing(AuditingTO auditingTO);
	List<AuditingTO> getAuditingByProductCode(String code);
	List<AuditingTO> listAllAuditing();

}
