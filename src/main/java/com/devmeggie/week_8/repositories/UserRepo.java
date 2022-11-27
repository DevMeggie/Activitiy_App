package com.devmeggie.week_8.repositories;

import com.devmeggie.week_8.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);

    User findByEmailAndPassword(String email, String password);


}
