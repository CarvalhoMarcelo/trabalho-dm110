package br.inatel.dm110.interfaces.product;

import br.inatel.dm110.api.product.ProductTO;

import java.util.List;

public interface Product {

	ProductTO getProductById(Integer code);
	List<ProductTO> getProductByName(String name);
	List<ProductTO> listAllProducts();
	void createProduct(ProductTO productTO);
	void updateProduct(ProductTO productTO);
	void deleteProduct(Integer id);

}
