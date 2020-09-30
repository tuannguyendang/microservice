package com.dangtuan.auth.repository;

import com.dangtuan.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

  String USER_QUERY = "SELECT DISTINCT u FROM User u WHERE u.userName = :username";
  String USER_NAME = "username";

  @Query(USER_QUERY)
  User findByUsername(@Param(USER_NAME) String username);
}
