package br.inatel.dm110.rest;


import br.inatel.dm110.impl.auditing.AuditingServiceImpl;
import br.inatel.dm110.impl.product.ProductServiceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(ProductServiceImpl.class); //register the class to publish the rest service
		classes.add(AuditingServiceImpl.class); //register the class to publish the rest service
		return classes;
	}
}
