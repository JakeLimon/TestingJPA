package com.jason.jpa;

import com.jason.jpa.entities.Employee;
import com.jason.jpa.entities.Products;
import com.jason.jpa.repository.EmployeeRepository;
import com.jason.jpa.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaApplicationTests {

	@Autowired
	ProductRepository repository;
	@Autowired
	private EmployeeRepository employeeRepository;


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

}
