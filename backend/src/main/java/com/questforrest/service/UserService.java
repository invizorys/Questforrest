package com.questforrest.service;

import com.questforrest.dto.RegistrationRequestDto;
import com.questforrest.dto.UserDto;
import com.questforrest.exception.InvalidTokenException;
import com.questforrest.exception.UserAlreadyExistException;
import com.questforrest.model.User;
import com.questforrest.repository.UserRepository;
import com.questforrest.util.UrlConnectionReader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by root on 08.10.16.
 */
@Service
public class UserService {
    private static final String VK_API_URL = "https://api.vk.com/method/users.get?access_token={token}";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UrlConnectionReader urlConnectionReader;

    @Transactional
    public UserDto register(RegistrationRequestDto requestDto) throws UserAlreadyExistException {
        User user = modelMapper.map(requestDto.getUserDto(), User.class);
        if (userRepository.findUserByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistException();
        }
        user.setPassword(encryptMD5(requestDto.getPassword()));
        user.setToken(UUID.randomUUID().toString().toUpperCase());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Transactional
    public UserDto login(String login, String password) {
        User user = userRepository.findUserByLogin(login);
        if (user != null && user.getPassword().equals(encryptMD5(password))) {
            return modelMapper.map(user, UserDto.class);
        }
        return null;
    }

    @Transactional
    public UserDto vkAuthorize(UserDto userDto, String token, String userId) throws InvalidTokenException {
        if (!isTokenValid(token, userId)) throw new InvalidTokenException();
        User user = userRepository.findUserByLogin(userDto.getLogin());
        if (user == null) {
            user = userRepository.save(modelMapper.map(userDto, User.class));
            user.setToken(UUID.randomUUID().toString().toUpperCase());
        }
        userDto.setId(user.getId());
        return userDto;
    }

    @Transactional
    public UserDto getUserByToken(String token) throws InvalidTokenException {
        if (token.isEmpty()) {
            throw new InvalidTokenException();
        }
        User user = userRepository.findUserByToken(token);
        return modelMapper.map(user, UserDto.class);
    }

    private boolean isTokenValid(String token, String userId) {
        String url = VK_API_URL.replace("{token}", token);
//      {"response":[{"uid":247830875,"first_name":"Testname","last_name":"Testsurname"}]}
        try {
            return urlConnectionReader.getText(url).contains("\"uid\":" + userId + ",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private String encryptMD5(final String input) {
        if (input == null) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
