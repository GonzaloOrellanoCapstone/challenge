package com.tenpo.challenge.component;

import com.tenpo.challenge.model.Role;
import com.tenpo.challenge.model.enums.ERole;
import com.tenpo.challenge.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.saveAll(Arrays.asList(
                new Role(1, ERole.ROLE_USER),
                new Role(2, ERole.ROLE_MODERATOR),
                new Role(3, ERole.ROLE_ADMIN)
        ));
    }
}
