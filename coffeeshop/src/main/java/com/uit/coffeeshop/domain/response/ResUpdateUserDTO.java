package com.uit.coffeeshop.domain.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.uit.coffeeshop.util.constant.Gender;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResUpdateUserDTO {
    private Long id;
    private String name;
    private int age;
    private Instant updatedAt;
    private Gender gender;
    private String address;
}
