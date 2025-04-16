package com.uit.coffeeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.uit.coffeeshop.domain.Role;
import com.uit.coffeeshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByEmail(String email);

    User findByRefreshTokenAndEmail(String refreshToken, String email);

    public boolean existsByEmail(String email);

    Long countByRoleNot(Role roleName);

}
