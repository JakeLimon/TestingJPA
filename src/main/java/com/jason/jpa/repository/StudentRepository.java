package com.jason.jpa.repository;

import com.jason.jpa.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {


    // Se debe poner como se llama mi clase Java y no como se llama de la DB
    // No es necesario poner select * ya que si vamos a obtener a todos los datos y por eso podemos omitirlo
    @Query("from Student")
    List<Student> findAllStudents();

    // En este caso debemos tener cuidado con el tipo de dato que vamos a retornar
    @Query("select score from Student")
    List<Integer> findAllStudentSpecialCase();

    // Vamos a crear una consulta multiple
    // Tener en cuenta en este ejemplo que  debemos poner los campos con estan en nuestra entidad
    @Query("select firstName from Student")
    List<String> finMultipleCase();

}
