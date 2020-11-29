/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vuong.repository;

import com.vuong.entity.Question;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ducvuong25
 */
public interface QuestionRepository extends CrudRepository<Question, Integer> {

    List<Question> findByContentContaining(String keyword);
}
