package com.example.mserver.service;

import com.example.mserver.entity.dto.NewServer;

import java.util.List;

public interface ServerService {

    public List<NewServer> getAllServers();
    public NewServer saveServerInDB(NewServer newServer);
    public NewServer findByServerId(int id);

    public  List<NewServer> findServerByName(String name);

    public String deleteServer(int id);

}
