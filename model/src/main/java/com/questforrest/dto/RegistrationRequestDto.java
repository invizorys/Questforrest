package com.questforrest.dto;

import java.io.Serializable;

/**
 * Created by root on 08.10.16.
 */
public class RegistrationRequestDto implements Serializable {
    private UserDto userDto;
    private String password;

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
