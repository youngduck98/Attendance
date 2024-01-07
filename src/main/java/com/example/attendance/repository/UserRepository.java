package com.example.attendance.repository;

import com.example.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findUserByUserUid(int uid);
    public Optional<User> findUserByUserUuid(String uuid);
}
