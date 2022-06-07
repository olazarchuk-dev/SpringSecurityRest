package com.st.authlight.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String role;
    private String login;
    private String password;
    private Boolean passwordExpired;
}
