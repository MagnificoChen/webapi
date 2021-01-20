package com.classdesign.webapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ysj
 */
@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    public List<Student> findAllByTid(String tid);
    public Student findByNumber(String number);
    public Student findById(int id);

}
