package com.wiktor.ecommerce;

import com.wiktor.ecommerce.Model.User;
import com.wiktor.ecommerce.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(value = false)
class EcommerceApplicationTests {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepository;
	@Test
	void testCreateUser() {
		User user=new User();
		user.setEmail("wbartoszyn@gmail.com");
		user.setPassword("wiktor");
		user.setName("Wiktor");
		user.setSurname("Bartoszyn");

		User savedUser=userRepository.save(user);

		User existUser= entityManager.find(User.class,savedUser.getId());
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	}

}
