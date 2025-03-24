package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByOrderByIdAsc();
    User findByName(String name);
}
