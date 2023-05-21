package br.inatel.dm110.impl.product;

import br.inatel.dm110.api.product.ProductService;
import br.inatel.dm110.api.product.ProductTO;
import br.inatel.dm110.interfaces.product.ProductLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@RequestScoped
public class ProductServiceImpl implements ProductService {

	private static final Logger log = Logger.getLogger(ProductServiceImpl.class.getName());

	@EJB
	private ProductLocal productLocalBean;

	@Override
	public Response getProductById(Integer code) {
		log.info("IMPL -> Retrieving product by code: " + code);
		ProductTO productTO = productLocalBean.getProductById(code);
		return Objects.nonNull(productTO) ? Response.ok(productTO).build() : Response.noContent().build();
	}

	@Override
	public Response getProductByName(String name) {
		log.info("IMPL -> Retrieving product by name: " + name);
		List<ProductTO> productTOList = productLocalBean.getProductByName(name);
		return Response.ok(productTOList).build();
	}

	@Override
	public List<ProductTO> listAllProducts() {
		log.info("IMPL -> Retrieving all products.");
		return productLocalBean.listAllProducts();
	}

	@Override
	public void createProduct(ProductTO productTO) {
		log.info("IMPL -> Creating product: " + productTO);
		productLocalBean.createProduct(productTO);
	}

	@Override
	public void updateProduct(Integer code, ProductTO productTO) {
		log.info("IMPL -> Updating product: " + productTO);
		productTO.setCode(code);
		productLocalBean.updateProduct(productTO);
	}

	@Override
	public void deleteProduct(Integer code) {
		log.info("IMPL -> Deleting product code: " + code);
		productLocalBean.deleteProduct(code);
	}

}
