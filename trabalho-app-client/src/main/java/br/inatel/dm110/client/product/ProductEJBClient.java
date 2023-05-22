package br.inatel.dm110.client.product;


import br.inatel.dm110.api.product.ProductTO;
import br.inatel.dm110.interfaces.product.ProductRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

public class ProductEJBClient {

	public static void main(String[] args) throws Exception {
		invokeStatelessBean();
	}

	private static void invokeStatelessBean() throws NamingException {

		final ProductRemote statelessProduct = lookupStatelessProduct();
		if (statelessProduct != null) {
			// remote object call invoke.
			String result = statelessProduct.toString();
			System.out.println("Result of stateless call: " + result);

			ProductTO product0TO = fillProduct("Laptop0", "Last generation new laptop0", "Technology", 5000.99);

			statelessProduct.createProduct(product0TO);
			System.out.println("Product id: " + product0TO.getCode());

			List<ProductTO> resp = statelessProduct.listAllProducts();
			System.out.println("\n\nProducts: \n" + resp);
		} else {
			System.out.println("No remote stateless object found!");
		}
	}

	private static ProductRemote lookupStatelessProduct() throws NamingException {
		// Stateless EJB(object) lookup

		String appName = "trabalho-ear-1.0";
		String moduleName = "trabalho-ejb-1.0";
		String beanName = "ProductBean";
		String interfaceName = ProductRemote.class.getName();

		// nome completo do EJB
		String jndiName = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + interfaceName;
		System.out.println("JNDI Name: " + jndiName);
		Context context = createInitialContext();
		return (ProductRemote) context.lookup(jndiName);
	}

	// JEE acess container properties configuration
	private static Context createInitialContext() throws NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		return new InitialContext(jndiProperties);
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