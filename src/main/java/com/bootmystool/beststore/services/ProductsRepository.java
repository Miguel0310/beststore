package com.bootmystool.beststore.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootmystool.beststore.models.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer>{

	
}
