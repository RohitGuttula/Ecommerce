package com.devRohit.Ecommerce.Entity;

import com.devRohit.Ecommerce.Enum.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    private UserRole role;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;




}
