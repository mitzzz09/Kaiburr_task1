package com.example.mserver.service;

import com.example.mserver.entity.dao.NewServerDao;
import com.example.mserver.entity.dto.NewServer;
import com.example.mserver.repository.ServerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Slf4j
@Service
public class ServerServiceImpl implements ServerService {
    private final ServerRepository serverRepository;
    private final ObjectMapper objectMapper;

    public ServerServiceImpl(ServerRepository serverRepository, ObjectMapper objectMapper) {
        this.serverRepository = serverRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<NewServer> getAllServers() {
        List<NewServerDao> serverDaoList= serverRepository.findAll();
        List<NewServer> serverList= new ArrayList<>();
        for (NewServerDao s:serverDaoList){
            serverList.add(objectMapper.convertValue(s,NewServer.class));
        }
        return serverList;
    }

    @Override
    public NewServer saveServerInDB(NewServer newServer) {
        NewServerDao serverDao = serverRepository.save(objectMapper.convertValue(newServer, NewServerDao.class));
        return newServer;
    }

    @Override
    public NewServer findByServerId(int  id) {
        Optional<NewServerDao> newServerDao= serverRepository.findById(id);
        //log.info("----------------------new server dao by id {}",newServerDao);
        if(newServerDao.isPresent()) {
            return objectMapper.convertValue(newServerDao.get(), NewServer.class);
        }
        return null;
    }

    @Override
    public List<NewServer> findServerByName(String name) {
        List<NewServerDao> newServerDao= serverRepository.findByName(name);
        List<NewServer> serverList= new ArrayList<>();
        for (NewServerDao s:newServerDao){
            serverList.add(objectMapper.convertValue(s,NewServer.class));
        }
        return serverList;
    }

    @Override
    public String deleteServer(int id) {
        Optional<NewServerDao> newServerDao= serverRepository.findById(id);
        if(newServerDao.isPresent()) {
            serverRepository.deleteById(id);
            return "Server is Deleted id is: "+ String.valueOf(id);
        }
        return "Server is not found with id : "+ String.valueOf(id);

    }


}
