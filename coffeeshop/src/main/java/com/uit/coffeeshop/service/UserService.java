package com.uit.coffeeshop.service;


import org.springframework.stereotype.Service;

import com.uit.coffeeshop.domain.User;

import com.uit.coffeeshop.domain.response.ResRegisterUserDTO;



import com.uit.coffeeshop.repository.UserRepository;

import com.uit.coffeeshop.util.error.IdInvalidException;


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

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User handleSaveUser(User postUser){
        return userRepository.save(postUser);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
