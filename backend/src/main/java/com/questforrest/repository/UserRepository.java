package com.questforrest.repository;

import com.questforrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.integration.dispatcher.LoadBalancingStrategy;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

/**
 * Created by root on 08.10.16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
