package br.inatel.dm110.product.beans.product;

import br.inatel.dm110.api.product.ProductTO;
import br.inatel.dm110.product.entities.auditing.Auditing;
import br.inatel.dm110.product.interfaces.mappers.ProductMapper;
import br.inatel.dm110.product.entities.product.Product;
import br.inatel.dm110.interfaces.product.ProductLocal;
import br.inatel.dm110.interfaces.product.ProductRemote;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
@Local(ProductLocal.class)
@Remote(ProductRemote.class) // change to Remote if necessary
public class ProductBean implements ProductLocal, ProductRemote {

	private static final Logger log = Logger.getLogger(ProductBean.class.getName());

	@PersistenceContext(unitName = "product_pu")
	private EntityManager em;

	@Inject
	private ProductMapper productMapper;

	@EJB
	private ProductQueueSender queueSender;

	private final static String QUERY_PARAMETERS = "from Product p";
	private final static String PRODUCT = "Product";

	public ProductBean(){}

	@Override
	public ProductTO getProductById(Integer code){
		log.info("EJB -> Retrieving product by code: " + code);
		Product product = em.find(Product.class, code);
		//send the action message to audit
		return Objects.nonNull(product) ? productMapper.productToProductDto(product) : null;
	}

	@Override
	public List<ProductTO> getProductByName(String name){
		log.info("EJB -> Retrieving product by name: " + name);
		String queryParameter = " where p.name like ?1";
		return toCollectionAPIModel(executeProductQuery(queryParameter).setParameter(1,name).getResultList());
	}

	@Override
	public List<ProductTO> listAllProducts() {
		log.info("EJB -> Retrieving all products!");
		return toCollectionAPIModel(executeProductQuery("").getResultList());
	}

	@Override
	public void createProduct(ProductTO productTO) {
		Product productEntity = productMapper.productDtoToProduct(productTO);
		log.info("EJB -> Saving product " + productEntity);
		// send to auditing
		em.persist(productEntity);
		queueSender.sendObjectMessage(createLog(PRODUCT, productEntity.getCode().toString(), "Create"));
	}

	@Override
	public void updateProduct(ProductTO productTO) {
		Product productEntity = productMapper.productDtoToProduct(productTO);
		log.info("EJB -> Updating product " + productEntity);
		//send to auditing
		queueSender.sendObjectMessage(createLog(PRODUCT, productTO.getCode().toString(), "Update"));
		em.merge(productEntity);
	}

	@Override
	public void deleteProduct(Integer code){
		log.info("EJB -> Deleting product id: " + code);
		Product product = em.find(Product.class, code);
		if(Objects.nonNull(product)){
			//send to auditing
			queueSender.sendObjectMessage(createLog(PRODUCT, code.toString(), "Delete"));
			em.remove(product);
		}
	}

	private List<ProductTO> toCollectionAPIModel(List<Product> productList) {
		return productList.stream().map(p -> productMapper.productToProductDto(p)).collect(Collectors.toList());
	}

	private TypedQuery<Product> executeProductQuery(String parameter){
		return em.createQuery(QUERY_PARAMETERS + parameter, Product.class);
	}

	private Auditing createLog(String registerFrom, String registerCode, String operaration){
		return new Auditing(registerFrom, registerCode, operaration, LocalDateTime.now());
	}


}
