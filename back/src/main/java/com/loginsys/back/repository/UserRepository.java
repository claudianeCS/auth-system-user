package com.loginsys.back.repository;

import com.loginsys.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByEmail(String email);

   Optional<User> findOneByEmailAndPassword(String email, String password);
}
