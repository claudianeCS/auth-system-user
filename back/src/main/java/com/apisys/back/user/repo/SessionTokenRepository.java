package com.apisys.back.user.repo;

import com.apisys.back.user.SessionTokenExpiration;
import com.apisys.back.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionTokenRepository extends JpaRepository<SessionTokenExpiration, UUID> {

   Optional<SessionTokenExpiration> findByToken(String token);

   Optional<SessionTokenExpiration> findByUser(User userId);

}
