package com.example.NewProject.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{

    @Query("select e from UserEntity e where e.email = :email")
    Optional<UserEntity> findByEmail(String email);
}
