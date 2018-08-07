package com.spring.jms.repository;

import com.spring.jms.model.Message;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface MessageRepository extends Repository<Message, Long> {

    Optional<Message> findById(Long id);

    Optional<List<Message>> findAll();

    Optional<Message> save(Message message);
}