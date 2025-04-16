package com.uit.coffeeshop.domain;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uit.coffeeshop.util.SecurityUtil;
import com.uit.coffeeshop.util.constant.AccountStatus;
import com.uit.coffeeshop.util.constant.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;

    @NotBlank(message = "email không được để trống")
    private String email;

    @NotBlank(message = "password không được để trống")
    private String password;

    private int age;

    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String accessToken;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String refreshToken;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean emailVerified;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
    
   
    @PrePersist
    public void handleBeforeCreate() {
        this.createdBy = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";

        this.createdAt = Instant.now();
    }
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="cart_id")
    private Cart cart;
    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedBy = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";

        this.updatedAt = Instant.now();
    }
}
