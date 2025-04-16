package com.uit.coffeeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.uit.coffeeshop.domain.Role;
import com.uit.coffeeshop.util.constant.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>,
                JpaSpecificationExecutor<Role> {
        boolean existsByName(RoleEnum name);

        Role findByName(RoleEnum name);
}
