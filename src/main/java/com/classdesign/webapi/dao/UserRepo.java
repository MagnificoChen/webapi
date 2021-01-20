package com.classdesign.webapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ysj
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    public User findById(int id);
}
