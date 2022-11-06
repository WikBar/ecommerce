package com.wiktor.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@Setter
@Getter
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name="Login")
    private String login;

    @Column(name="Email")
    private String email;

    @Column(name="Surname")
    private String password;

}
