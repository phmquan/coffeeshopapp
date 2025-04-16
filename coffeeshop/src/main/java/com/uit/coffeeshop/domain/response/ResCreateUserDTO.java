/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uit.coffeeshop.domain.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.uit.coffeeshop.util.constant.Gender;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCreateUserDTO {
    private Long id;
    private String email;
    private String name;
    private int age;
    private Instant createdAt;
    private Gender gender;
    private String address;
}
