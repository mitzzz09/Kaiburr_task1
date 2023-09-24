package com.example.mserver.repository;

import com.example.mserver.entity.dao.NewServerDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServerRepository  extends MongoRepository<NewServerDao, Integer> {

    List<NewServerDao> findByName(String name);

}
