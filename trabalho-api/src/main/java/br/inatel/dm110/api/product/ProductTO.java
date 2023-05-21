package br.inatel.dm110.api.product;

import java.io.Serializable;

public class ProductTO implements Serializable {

	private Integer code;
	private String name;
	private String description;
	private Double price;
	private String category;

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
		return "ProductTO{" +
				"code=" + code +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", category='" + category + '\'' +
				'}';
	}
}
