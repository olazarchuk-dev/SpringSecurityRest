package com.st.authlight.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginDTO extends ClientDTO {

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @ToString.Exclude
    @NotBlank(message = "Password cannot be empty")
    private String password;

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}