package com.questforrest.repository;

import com.questforrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.integration.dispatcher.LoadBalancingStrategy;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByToken(String token);
    User findOneByLogin(String login);
}
