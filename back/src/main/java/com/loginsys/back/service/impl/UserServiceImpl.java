package com.loginsys.back.service.impl;

import com.loginsys.back.entity.LoginMessage;
import com.loginsys.back.entity.User;
import com.loginsys.back.entity.dto.LoginDTO;
import com.loginsys.back.entity.dto.UserDTO;
import com.loginsys.back.repository.UserRepository;
import com.loginsys.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String addUser(UserDTO userDTO) {

        User user = new User(userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()
                ));

        userRepository.save(user);

        return user.getUsername();
    }

    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";

        User user = userRepository.findByEmail(loginDTO.getEmail());
        if(user != null){
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();

            Boolean passValidation = passwordEncoder.matches(password, encodedPassword);
            if (passValidation){
                Optional<User> user1 = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user1.isPresent()){
                    return new LoginMessage("Logado com sucesso", true);
                } else {
                    return new LoginMessage("Erro password ou username esta errado", false);
                }
            } else {
                return new LoginMessage("Senha incorreta", false);
            }
        } else {
            return new LoginMessage("Email incorreto", false);
        }

    }
}
