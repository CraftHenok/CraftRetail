package com.myretailcompany.dataaccesslayer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
 
@Entity
@Table(name = "ProductCategory_MASTER")

public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 

	@NotNull
	private String name;
 

	public ProductCategory() {
		super();
	}

	public ProductCategory(  String name) {
		super();
	
		this.name = name; 
	}

 

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
 
 

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
 
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
