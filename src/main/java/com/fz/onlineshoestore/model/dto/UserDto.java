package com.fz.onlineshoestore.model.dto;

import com.fz.onlineshoestore.model.UserObj;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;

    public UserObj toUserObj() {
        return new UserObj(username, password, email);
    }
}
