package com.jason.jpa.repository;

import com.jason.jpa.entities.MyProducts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MyProductsRepository extends CrudRepository<MyProducts, Integer> {

    List<MyProducts> findByName(String name);

    List<MyProducts> findByPrice(Integer price);

}
