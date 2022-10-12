package com.filicedaniele.projectFG.repository;

import com.filicedaniele.projectFG.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByEmail(String email);
    List<User> findByNome(String nome);
    List<User> findByCognome(String cognome);
    List<User> findByNomeAndCognome(String nome,String cognome);
    Boolean existsByEmail(String email);
}
