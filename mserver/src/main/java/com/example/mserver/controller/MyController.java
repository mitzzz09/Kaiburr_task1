package com.example.mserver.controller;

import com.example.mserver.entity.dto.NewServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mserver.service.ServerService;

import java.util.List;
import java.util.Objects;

@RestController
public class MyController {
    private final ServerService serverService;

    public MyController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/start")
    public String home(){
        return  "Welcome to server";
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveServer(@RequestBody NewServer newserver) {
        return ResponseEntity.ok(this.serverService.saveServerInDB(newserver));
    }
    @GetMapping("/server")
    public ResponseEntity<?> getAllServers() {
        return ResponseEntity.ok(this.serverService.getAllServers());
    }

    @GetMapping("/server/{id}")
    public ResponseEntity<?> findServerById(@PathVariable("id")int id) {
        NewServer server = this.serverService.findByServerId(id);
        return (!Objects.isNull(server))?ResponseEntity.ok(server):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/server/name/{name}")
    public ResponseEntity<?> findServerByName(@PathVariable("name")String name) {
        List<NewServer> server = this.serverService.findServerByName(name);
        return (!(server.size()==0))?ResponseEntity.ok(server):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteServer(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.serverService.deleteServer(id));
    }





}
