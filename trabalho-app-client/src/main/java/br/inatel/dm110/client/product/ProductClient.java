package br.inatel.dm110.client.product;

import br.inatel.dm110.api.product.ProductTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ProductClient {

	private static final String REST_URI = "http://localhost:8080/trabalho-web/api/product";
	private static final String REST_URI_GET_ALL = "http://localhost:8080/trabalho-web/api/products";

	private static final Client client = ClientBuilder.newClient();

	public static void main(String[] args) {
		System.out.println("Product service:");
		
		ProductTO productTO = new ProductTO();
		productTO.setCode(1);
		productTO.setName("Laptop");
		productTO.setDescription("Last generation new laptop");
		productTO.setCategory("Technology");
		productTO.setPrice(5000.99);

		System.out.println("Result from POST: " + createProduct(productTO));

		System.out.println("Result from GET 0 by id: " + getProductById(0));
		System.out.println("Result from GET 1 by id: " + getProductById(1));
		System.out.println("Result from GET 2 by id: " + getProductById(2));

		System.out.println("Result from GET 0 by name: " + getProductByNamne("Laptop"));
		System.out.println("Result from GET 1 by name: " + getProductByNamne("Printer"));
		System.out.println("Result from GET 2 by name: " + getProductByNamne("Desktop"));

		System.out.println("Result of all product: " + getAllProducts());

		System.out.println("Result from DELETE 0: " + getProductByNamne("Laptop"));
		System.out.println("Result from DELETE 2: " + getProductByNamne("Printer"));

		System.out.println("Result of all product: " + getAllProducts());

	}

	public static ProductTO getProductById(Integer id) {
		return client
				.target(REST_URI)
				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.get(ProductTO.class);
	}

	public static ProductTO getProductByNamne(String name) {
		return client
				.target(REST_URI)
				.path(name)
				.request(MediaType.APPLICATION_JSON)
				.get(ProductTO.class);
	}

	public static List<ProductTO> getAllProducts() {
		List<ProductTO> list = client
				.target(REST_URI_GET_ALL)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(new GenericType<List<ProductTO>>() {});
		return list;
	}

	public static Response createProduct(ProductTO productTO) {
	    return client
	      .target(REST_URI)
	      .request(MediaType.APPLICATION_JSON)
	      .post(Entity.entity(productTO, MediaType.APPLICATION_JSON));
	}

	public static void delteProduct(Integer id) {
		client	.target(REST_URI)
				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.delete(ProductTO.class);
	}

}
