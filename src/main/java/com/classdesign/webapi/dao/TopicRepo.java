package com.classdesign.webapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ysj
 */
@Repository
public interface TopicRepo extends JpaRepository<Topic, Integer> {
    public Topic findById(int id);
    public List<Topic> findAllByTeacher(String teacher);
}
