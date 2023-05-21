package br.inatel.dm110.api.product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public interface ProductService {

	@GET
	@Path("product/code/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getProductById(@PathParam("code") Integer code);

	@GET
	@Path("product/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getProductByName(@PathParam("name") String name);

	@GET
	@Path("products")
	@Produces(MediaType.APPLICATION_JSON)
	List<ProductTO> listAllProducts();

	@POST
	@Path("product")
	@Consumes(MediaType.APPLICATION_JSON)
	void createProduct(ProductTO productTO);

	@PUT
	@Path("product/code/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	void updateProduct(@PathParam("code") Integer code, ProductTO productTO);

	@DELETE
	@Path("product/code/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	void deleteProduct(@PathParam("code") Integer code);

}
