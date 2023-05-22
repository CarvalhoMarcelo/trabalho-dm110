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
		
		ProductTO product0TO = fillProduct("Laptop0", "Last generation new laptop0", "Technology", 5000.99);
		ProductTO product1TO = fillProduct("Laptop1", "Last generation new laptop1", "Technology", 5001.99);
		ProductTO product2TO = fillProduct("Laptop2", "Last generation new laptop2", "Technology", 5002.99);
		System.out.println("Result from POST1: " + createProduct(product0TO));
		System.out.println("Result from POST2: " + createProduct(product1TO));
		System.out.println("Result from POST3: " + createProduct(product2TO));

		System.out.println("Result from GET 0 by id: " + getProductById(0));
		System.out.println("Result from GET 1 by id: " + getProductById(1));
		System.out.println("Result from GET 2 by id: " + getProductById(2));

		System.out.println("Result of all product: " + getAllProducts());

		System.out.println("Result from DELETE product: " + deleteProduct(0));

		System.out.println("Result of all product: " + getAllProducts());

	}

	public static ProductTO getProductById(Integer index) {
		return client
				.target(REST_URI + "/code/" + getAllProducts().get(index).getCode().toString())
				.request(MediaType.APPLICATION_JSON)
				.get(ProductTO.class);
	}

	public static ProductTO getProductByNamne(Integer index) {
		return client
				.target(REST_URI + "/name/" + getAllProducts().get(index).getName())
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

	public static ProductTO deleteProduct(Integer index) {
		ProductTO productTO = getAllProducts().get(index);
		return client
				.target(REST_URI + "/code/" + productTO.getCode())
				.request(MediaType.APPLICATION_JSON)
				.delete(ProductTO.class);
	}

	private static ProductTO fillProduct(String  name, String description, String category, Double price){
		ProductTO productTO = new ProductTO();
		productTO.setName(name);
		productTO.setDescription(description);
		productTO.setCategory(category);
		productTO.setPrice(price);

		return productTO;
	}


}
