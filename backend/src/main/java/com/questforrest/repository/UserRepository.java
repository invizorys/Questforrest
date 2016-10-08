package com.questforrest.repository;

import com.questforrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByToken(String token);
}
