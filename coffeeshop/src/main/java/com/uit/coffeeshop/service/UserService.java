package com.uit.coffeeshop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uit.coffeeshop.domain.User;
import com.uit.coffeeshop.domain.response.ResCreateUserDTO;
import com.uit.coffeeshop.domain.response.ResRegisterUserDTO;
import com.uit.coffeeshop.domain.response.ResUpdateUserDTO;
import com.uit.coffeeshop.domain.response.ResUserDTO;
import com.uit.coffeeshop.domain.response.RestResponse;
import com.uit.coffeeshop.domain.response.ResultPaginationDTO;
import com.uit.coffeeshop.domain.response.ResultPaginationDTO.Meta;

import com.uit.coffeeshop.repository.RoleRepository;
import com.uit.coffeeshop.repository.UserRepository;
import com.uit.coffeeshop.util.constant.AccountStatus;
import com.uit.coffeeshop.util.error.IdInvalidException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User handleGetUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    public void updateUserToken(String refresh_token, String username) {
        User updatedUser=userRepository.findByEmail(username);
        updatedUser.setRefreshToken(refresh_token);
        userRepository.save(updatedUser);
    }

    public boolean isEmailExist(String email) {
       return userRepository.existsByEmail(email);
    }

    public User handleCreateUser(User postmanUser) throws IdInvalidException {
        if(userRepository.existsByEmail(postmanUser.getEmail())){
            throw new IdInvalidException("Email đã tồn tại");
        }
        else{
            return userRepository.save(postmanUser);
        }
    }

    public ResRegisterUserDTO convertToResRegisterUserDTO(User currentUser) {
        ResRegisterUserDTO resRegisterUserDTO=new ResRegisterUserDTO();
        resRegisterUserDTO.setId(currentUser.getId());
        resRegisterUserDTO.setEmail(currentUser.getEmail());
        resRegisterUserDTO.setName(currentUser.getName());
        resRegisterUserDTO.setCreatedAt(currentUser.getCreatedAt());
        return resRegisterUserDTO;
    }

    public User getUserByRefreshTokenAndEmail(String refresh_token, String email) {
        return userRepository.findByRefreshTokenAndEmail(refresh_token, email);
    }

}
