package br.inatel.dm110.product.entities.product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT", schema = "public")
public class Product implements Serializable {

	private static final long serialVersionUID = -3226154108429480413L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CODE", unique = true)
	private Integer code;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "CATEGORY")
	private String category;


	public Product() {} //default constructor

	public Product(Integer code, String name, String description, String category, Double price) {
		super();

		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product{" +
				"code=" + code +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", category='" + category + '\'' +
				'}';
	}
}
