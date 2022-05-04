package com.jason.jpa.repository;

import com.jason.jpa.entities.Productos;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends PagingAndSortingRepository<Productos, Integer> {
}
