package com.apisys.back;

import com.apisys.back.user.Role;
import com.apisys.back.user.User;
import com.apisys.back.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitializerApplication implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (userRepository.findByEmail("apisys@teste.com") == null){
            User user = new User("root", "apisys@teste.com", passwordEncoder.encode( "12348765"));
            user.setRole(Role.ADMIN);
            user.setCreatedAt(new Date());
            userRepository.save(user);
        }
    }
}
