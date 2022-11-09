package com.wiktor.ecommerce;

import com.wiktor.ecommerce.Model.User;
import com.wiktor.ecommerce.Repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EcommerceApplicationTests {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepository;


	@Test
	@Order(1)
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
	@Order(2)

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
	@Order(3)
	public void getUserTest(){
		User user=userRepository.findById(1).get();
		assertThat(user.getId()).isEqualTo(1);
	}
	@Test
	@Order(4)
	public void getListOfUsersTest(){
		List<User> userList= userRepository.findAll();
		assertThat(userList.size()).isGreaterThan(0);
	}
	@Test
	@Order(5)
	public void updateUserTest(){
		User user =userRepository.findById(1).get();
		user.setEmail("rafalToPala@smietnik-pwr.pl");
		User userUpdated=userRepository.save(user);
		assertThat(userUpdated.getEmail()).isEqualTo("rafalToPala@smietnik-pwr.pl");
	}

	@Test
	@Order(6)
	public void deleteUserTest(){
		User user=userRepository.findById(1).get();
		userRepository.delete(user);
		User user1=null;
		Optional<User> optionalUser=userRepository.findByEmail("wbartoszyn@gmail.com");
		if (optionalUser.isPresent()){
			user1=optionalUser.get();
		}

		assertThat(user1).isNull();
	}

}
