package com.myretailcompany.rest.controller;

import java.net.URI;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myretailcompany.dataaccesslayer.entity.ProductCategory; 
import com.myretailcompany.service.ProductCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

// Entity Beans are used and returned by this call to web layer. Ideally they should be different.

@RestController
@Api(value = "onlinestore",description="Manage Product Categorys")
public class ProductCategoryController {

	final Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private ProductCategoryService ProductCategoryService;

	@ApiOperation(value = "Create a new ProductCategory", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "id <id> created"),
			@ApiResponse(code = 401, message = "Bad Credentials") })
	@RequestMapping(value = "/ProductCategorys", method = RequestMethod.POST)
	public ResponseEntity<ProductCategory> createProductCategory(
			@ApiParam(value = "Data for the new ProductCategory", required = true) @Valid @RequestBody ProductCategory ProductCategory) {
		logger.info("Input recieved to create ProductCategory = " + ProductCategory);
		ProductCategory ProductCategoryinfo = ProductCategoryService.createProductCategory(ProductCategory);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ProductCategory.getId())
				.toUri();
		logger.info("Setting header url with newly created ProductCategory= " + ProductCategory.getId());
		responseHeaders.setLocation(newPollUri);
		return new ResponseEntity<>(ProductCategory, responseHeaders, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Delete existing ProductCategory", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "id <id> deleted"),
			@ApiResponse(code = 401, message = "Bad Credentials") })
	@RequestMapping(value = "/ProductCategorys/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProductCategory(@PathVariable Long id) {
		ProductCategoryService.deleteProductCategory(id);
		return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);
	}

	@ApiOperation(value = "View list of available ProductCategorys", response = Iterable.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "Bad Credentials") })
	@RequestMapping(value = "/ProductCategorys", method = RequestMethod.GET)
	public ResponseEntity<Iterable<ProductCategory>> getAllProductCategorys() {
		return new ResponseEntity<>(ProductCategoryService.getAllProductCategorys(), HttpStatus.OK);
	}

	@ApiOperation(value = "View a specific ProductCategory", response = ProductCategory.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved ProductCategory details"),
			@ApiResponse(code = 401, message = "Bad Credentials"),
			@ApiResponse(code = 404, message = "ProductCategory does not exist") })
	@RequestMapping(value = "/ProductCategorys/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductCategory> getProductCategoryById(
			@ApiParam(value = "id of a particular ProductCategory", required = true) @PathVariable Long id) {
		return new ResponseEntity<>(ProductCategoryService.getProductCategoryById(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Update existing ProductCategory", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "id <id> updated"),
			@ApiResponse(code = 401, message = "Bad Credentials") })

	@RequestMapping(value = "/ProductCategorys/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProductCategory> updateProductCategory(@Valid @RequestBody ProductCategory ProductCategory, @PathVariable Long id) {
		ProductCategory prod = ProductCategoryService.updateProductCategory(ProductCategory, id);
		logger.info("updated ProductCategory id = " + prod.getId());
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}

}
