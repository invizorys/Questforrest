package com.questforrest.service;

import com.questforrest.dto.RegistrationRequestDto;
import com.questforrest.dto.UserDto;
import com.questforrest.model.User;
import com.questforrest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by root on 08.10.16.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UserDto register(RegistrationRequestDto requestDto) {
        User user = modelMapper.map(requestDto.getUserDto(), User.class);
        user.setPassword(encriptMD5(requestDto.getPassword()));
        user.setToken(UUID.randomUUID().toString().toUpperCase());
        UserDto savedUser =  modelMapper.map(userRepository.save(user), UserDto.class);
        return savedUser;
    }

    public String encriptMD5(final String input) {
        String hash = null;
        if(null == input) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            hash = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
