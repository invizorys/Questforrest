package com.questforrest.dto;

import java.io.Serializable;

/**
 * Created by root on 08.10.16.
 */
public class RegistrationRequestDto implements Serializable {
    private UserDto userDto;
    private String password;
    private String accessToken;
    private String userId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
