package com.wiktor.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@Setter
@Getter

@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name="Email",nullable = false,length=64)
    private String email;

    @Column(name="Login",nullable = false,unique = true,length = 50)
    private String login;

    @Column(name="Password")
    private String password;

    @Column(name="Name",nullable = false,length = 25)
    private String name;

    @Column(name="Surname",nullable = false,length = 25)
    private String surname;




    public User(String login,String surname,String name, String email,String password){
        this.email=email;
        this.login=login;
        this.name=name;
        this.surname=surname;
        this.password=password;
    }

}
