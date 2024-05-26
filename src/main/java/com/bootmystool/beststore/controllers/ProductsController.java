package com.bootmystool.beststore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootmystool.beststore.models.ProductDto;
import com.bootmystool.beststore.models.Products;
import com.bootmystool.beststore.services.ProductsRepository;

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

}
