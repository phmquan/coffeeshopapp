package com.uit.coffeeshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.coffeeshop.domain.User;
import com.uit.coffeeshop.domain.response.ResLoginDTO;
import com.uit.coffeeshop.service.UserService;
import com.uit.coffeeshop.util.SecurityUtil;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final SecurityUtil securityUtil;
    @PutMapping("")
    public ResponseEntity<ResLoginDTO.UserLogin> putMethodName(
        @RequestHeader(value="Authorization") String authorizationToken,
        @RequestBody ResLoginDTO.UserLogin editUser
    ) {
        String accessToken="";
        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            accessToken = authorizationToken.substring(7); // Remove "Bearer "
        }
        Jwt decodedToken=this.securityUtil.checkValidRefreshToken(accessToken);
        String email=decodedToken.getSubject();
        User currentUser=this.userService.getUserByEmail(email);
        currentUser.setName(editUser.getName());
        currentUser.setAvatar(editUser.getAvatar());
        currentUser=userService.handleSaveUser(currentUser);
        return ResponseEntity.ok(new ResLoginDTO.UserLogin(currentUser.getId(),currentUser.getName(),currentUser.getEmail(),currentUser.getAvatar()));
    }
}
