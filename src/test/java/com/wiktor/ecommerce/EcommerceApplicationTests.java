package com.wiktor.ecommerce;

import com.wiktor.ecommerce.Model.User;
import com.wiktor.ecommerce.Repository.UserRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
class EcommerceApplicationTests {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepository;


	@Test
	void testCreateUser() {

		//given
		User user = new User();
		user.setEmail("wbartoszyn@gmail.com");
		user.setLogin("Wiciu1221");
		user.setPassword("wiktor");
		user.setName("Wiktor");
		user.setSurname("Bartoszyn");

		//when
		User savedUser = userRepository.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());

		//then
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

	}

	@Test
	void testCreateUserAllDataCorrect(){

		//given
		User user=new User();
		user.setEmail("wbartoszyn1@gmail.com");
		user.setLogin("Wiciu1222");
		user.setPassword("wiktor");
		user.setName("Wiktor");
		user.setSurname("Bartoszyn");

		//when
		User savedUser=userRepository.save(user);
		User existUser=entityManager.find(User.class,savedUser.getId());

		//then
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		assertThat(user.getLogin()).isEqualTo(existUser.getLogin());
		assertThat(user.getName()).isEqualTo(existUser.getName());
		assertThat(user.getPassword()).isEqualTo(existUser.getPassword());
		assertThat(user.getSurname()).isEqualTo(existUser.getSurname());
	}

	@Test
	@Transactional
	void testCreateUserAllDeleted(){

		//given
		User user=new User();
		user.setEmail("wbartoszyn1@gmail.com");
		user.setLogin("Wiciu1222");
		user.setPassword("wiktor");
		user.setName("Wiktor");
		user.setSurname("Bartoszyn");



		//when

		userRepository.delete(user);
		if(Objects.nonNull(entityManager.find(User.class,"wbartoszyn1@gmail.com"))){
			User userNotExists = entityManager.find(User.class,"wbartoszyn1@gmail.com");

			//then
			assertThat(userNotExists).isNull();
		}else{
			assert(true);
		}
	}

}
