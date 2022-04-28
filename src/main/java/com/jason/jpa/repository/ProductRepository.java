package com.jason.jpa.repository;

import com.jason.jpa.entities.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Products, Integer> {
}
