package com.read.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.read.biblioteca.model.Book;
import com.read.biblioteca.model.OurUsers;

import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String message;
    private String error;

    private Book ourBook; // Para un solo libro
    private List<Book> books;


    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String lastname;
    private String genero;
    private String city;
    private String role;
    private String email;
    private String password;
    private OurUsers ourUsers;
    private List<OurUsers> ourUsersList;
}
