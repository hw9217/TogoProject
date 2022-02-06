package com.example.myTriple.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignupForm {
    @NotBlank
    @Length(min = 5, max = 40)
    @Email
    private String email;

    @NotBlank
    private String password;

    private String name;
    private String phone;
    private String zipcode;
}
