package com.jason.jpa.repository;

import com.jason.jpa.entities.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {


    // Se debe poner como se llama mi clase Java y no como se llama de la DB
    // No es necesario poner select *, ya que si vamos a obtener a todos los datos y por eso podemos omitirlo
    @Query("from Student")
    List<Student> findAllStudents();

    // En este caso debemos tener cuidado con el tipo de dato que vamos a retornar
    @Query("select score from Student")
    List<Integer> findAllStudentSpecialCase();

    // Vamos a crear una consulta multiple
    // Tener en cuenta en este ejemplo que debemos poner los campos como están en nuestra entidad
    @Query("select firstName from Student")
    List<String> finMultipleCase();

    // Ahora si vamos a casos multiples
    @Query("select firstName, lastName from Student")
    List<Object[]> findMultiplesCases();

    // Vamos a crear un metodo donde recibiremos un parametro
    // Tener en cuenta la consulta debera usar como están en la entidad y el =: usara el que está en el @Param
    @Query("from Student where firstName=:first_name")
    List<Student> findWithParameter(@Param("first_name") String firstName);

    // Podemos usar un between recibiendo ambos parametros
    @Query("from Student where score>:min and score<=:max")
    List<Student> findBetweenScore(@Param("min") int min, @Param("max") int max);

    // Eliminando un registro
    @Modifying
    @Query("delete from Student where firstName = :first_name")
    void deleteStudentByFirstName( @Param("first_name") String firstName );

    // aqui vamos a ejecutar Navite Query ya que si notamos en nuestro Query ponemos como esta en la base de datos y no como aparece
    // en la entidad
    @Query(value = "select * from student", nativeQuery = true)
    List<Student> findStudentsByNQ();

    // Usando Native Query ahora vamos a pasar un parametro
    @Query(value = "select * from student where first_name = :fName", nativeQuery = true)
    List<Student> findNQParameter(@Param("fName") String firstName);

}
