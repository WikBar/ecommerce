package com.wiktor.ecommerce.Repository;

import com.wiktor.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
