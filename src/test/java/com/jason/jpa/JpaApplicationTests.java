package com.jason.jpa;

import com.jason.jpa.entities.Employee;
import com.jason.jpa.entities.MyProducts;
import com.jason.jpa.entities.Products;
import com.jason.jpa.repository.EmployeeRepository;
import com.jason.jpa.repository.MyProductsRepository;
import com.jason.jpa.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

}
