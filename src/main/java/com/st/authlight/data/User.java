package com.st.authlight.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String login;

    private String password;

    private String firstname;

    private String lastname;

    private Integer age;

}
