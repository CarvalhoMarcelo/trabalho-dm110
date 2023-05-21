package br.inatel.dm110.api.auditing;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public interface AuditingService {

	@GET
	@Path("auditing/productCode/{productCode}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getAuditingByProductCode(@PathParam("productCode") String productCode);

	@GET
	@Path("auditing")
	@Produces(MediaType.APPLICATION_JSON)
	List<AuditingTO> listAllAuditing();

}
