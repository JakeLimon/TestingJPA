package com.jason.jpa;

import com.jason.jpa.entities.*;
import com.jason.jpa.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaApplicationTests {

	@Autowired
	ProductRepository repository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	MyProductsRepository myProductsRepository;
	@Autowired
	ProductosRepository productosRepository;
	@Autowired
	StudentRepository studentRepository;


	@Test
	void contextLoads() {
	}

	@Test
	public void testCreated(){
		Products product = new Products();
		product.setId(1);
		product.setName("Iphone");
		product.setDesc("Awesome");
		product.setPrice(1000d);

		repository.save(product);
	}

	@Test
	public void testRea(){
		Products product = repository.findById(1).get();
		assertNotNull(product);
		assertEquals("Iphone", product.getName());
		assertEquals("Awesome", product.getDesc());
	}

	@Test
	public void testUpdate(){
		Products product = repository.findById(1).get();
		product.setPrice(2000d);
		repository.save(product);
	}

	@Test
	public void testDelete(){
		if( repository.existsById(1) )
			repository.deleteById(1);
	}

	@Test
	public void testCount(){
		System.out.println("Total records "+repository.count());
	}

	// Aqui vamos a testear nuestro generador propio para los ID

	@Test
	public void testSaveEmployee(){
		Employee employee = new Employee();
		employee.setName("Jason");

		employeeRepository.save(employee);
	}

	// Aqui por repasar voy hacer el insert con un test para mi entidad MyProducts

	@Test
	public void testMyProductsSave(){
		MyProducts product = new MyProducts();
		product.setName("Dell");
		product.setDesc("Laptop para tus tareas marca DELL");
		product.setPrice(23_000);
		myProductsRepository.save(product);

	}

	@Test
	public void testFindByName(){
		List<MyProducts> products = myProductsRepository.findByName("IPhone");
		products.forEach(p -> System.out.println("El " + p.getName() +" cuesta " +p.getPrice()));
	}

	// En este test vere como funciona cundo el nombre es fierente en la base de datos pero en mi entidad es otro

	@Test
	public void testOtherName(){
		List<MyProducts> products = myProductsRepository.findByPrice(25000);
		products.forEach(p -> System.out.println("Diferente nombre en la base de datos. El " + p.getName() +" cuesta " +p.getPrice()));
	}

	// A partir de aqui vamos a crear los test para las paginaciones

	@Test
	public void testSaveProducto(){
		Productos producto = new Productos();
		producto.setNombre("Guitarra Epihone");
		producto.setDesc("Demuestra que eres el mejor rockero de la ciudad");
		productosRepository.save(producto);
	}

	// Una vez guardados los valores camos a crar un teste donde aplicamos la paginacion

	@Test
	public void testFindAllPaging(){
		Page<Productos> page = productosRepository.findAll(PageRequest.of(0,3));
		page.forEach( data -> System.out.printf("%s es %s y la descripcion es: %s \n",data.getId(), data.getNombre(), data.getDesc()));
	}

	@Test
	public void testFindAllSort(){
//		Iterable<Productos> results = productosRepository.findAll(Sort.by("nombre").descending().and(Sort.by("desc").ascending()));
		Iterable<Productos> results = productosRepository.findAll(Sort.by("desc").descending().and(Sort.by("nombre").ascending()));
		results.forEach(data -> System.out.printf("%s =====> %s \n", data.getDesc(), data.getNombre()));
	}

	// Vamos a estudiar un poco de JPQL asi que se creo una tabla de student para este ejercicio y vamos a guardar un par de datos

	@Test
	public void testSaveStudent(){
		Student student = new Student();
		student.setFirstName("Jason");
		student.setLastName("Limon");
		student.setScore(10);

		studentRepository.save(student);
	}

	// Primer test para obtener a todos los estudiantes
	@Test
	public void testFindAllJPQL(){
		studentRepository.findAllStudents().forEach( s -> System.out.println(s.toString()));
	}

	@Test
	public void testFindAllSpecialCase(){
		studentRepository.findAllStudentSpecialCase().forEach( s -> System.out.println(s.toString()));
	}

	@Test
	public void testMultipleCase(){
		studentRepository.finMultipleCase().forEach(System.out::println);
	}

	// Ahora si el test de multiples casos
	@Test
	public void testMultipleCases(){
		studentRepository.findMultiplesCases().forEach(student -> System.out.println(student[0] +" " +student[1]));
	}

	@Test
	public void testFindWithParameter(){
		studentRepository.findWithParameter("Jay").forEach(s -> System.out.println(s.toString()));
	}

	@Test
	public void testMultipleParameters(){
		studentRepository.findBetweenScore(7, 10).forEach(s -> System.out.println(s.toString()));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testDeleteByFirstName(){
		studentRepository.deleteStudentByFirstName("Giovani");
	}

	@Test
	public void testNQ(){
		studentRepository.findStudentsByNQ().forEach(s -> System.out.println(s.toString()));
	}

	@Test
	public void testNQParameter(){
		studentRepository.findNQParameter("Jason").forEach(s -> System.out.println(s.toString()));
	}

}
