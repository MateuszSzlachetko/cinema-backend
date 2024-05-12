package com.piisw.cinema.auth.model;

import com.piisw.cinema.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    private String password;

    private UserType role;
}
