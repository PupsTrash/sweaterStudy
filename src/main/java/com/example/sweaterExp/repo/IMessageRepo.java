package com.example.sweaterExp.repo;

import com.example.sweaterExp.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
