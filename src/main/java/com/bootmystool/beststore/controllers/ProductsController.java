package com.bootmystool.beststore.controllers;

import java.util.List;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootmystool.beststore.models.ProductDto;
import com.bootmystool.beststore.models.Products;
import com.bootmystool.beststore.services.ProductsRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductsRepository repo;
	
	
	@GetMapping({"", "/"})
	public String showProductList(Model model) {
		//Lista dos produtos e filtro de forma DESC pelo ID
		List<Products> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("products", products);
		return "products/index";
	}
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		ProductDto productoDto = new ProductDto();
		model.addAttribute("productDto", productoDto);
		return "products/CreateProduct";
	}
	
	@PostMapping("/create")
	public String createProduct(
		@Valid @ModelAttribute ProductDto productDto,
		BindingResult result
	) {
		
		if(result.hasErrors()) {
			return "products/CreateProduct";
		}
		
		Products product = new Products();
		product.setName(productDto.getName());
		product.setBrand(productDto.getBrand());
		product.setCategory(productDto.getCategory());
		product.setPrice(productDto.getPrice());
		
		repo.save(product);
		
		return "redirect:/products";
	}
	

}
