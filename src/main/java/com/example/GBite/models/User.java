package com.example.GBite.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String password, boolean isActive, Set<Roles> roles) {
        this.username = username;
        this.password = password;
        this.isActive = true;
        this.roles = roles;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean isActive;

    @OneToOne(optional = true, mappedBy = "User")
    public persdan persdan;

    @OneToOne(optional = true, mappedBy = "User")
    public Brone Brone;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;
    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}

