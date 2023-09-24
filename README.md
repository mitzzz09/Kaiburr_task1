
# Step 1: Set Up Your Development Environment

1. Install Java: Download and install the latest version of Java Development Kit (JDK).

2. Install MongoDB: Download and install MongoDB and start a MongoDB server.

3. Create a new Spring Boot project using a tool like Spring Initializr.

# Step 2: Set Up Dependencies
Add the necessary dependencies to your pom.xml file. Browse spring initializr on the web 

    <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.28</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

# Step 3: Create a Model
Create a Server class to represent the server object:

    import org.springframework.data.annotation.Id;

    import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "servers")

    public class Server {
    @Id
    private String id;
    private String name;
    private String language;
    private String framework;

    // Constructors, getters, and setters
}
# Step 4: Create a Repository

Create a repository interface for accessing MongoDB:

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

# Step 5: Create a Controller

Create a REST controller to handle the endpoints:


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

# Step 6: Configure MongoDB

Add MongoDB configuration to your application.properties:

    spring.data.mongodb.host=localhost
    spring.data.mongodb.port=27017
    spring.data.mongodb.database=my-database

 Run your Spring Boot application. You can use Postman to test your REST API by sending GET, PUT, and DELETE requests to the appropriate endpoints.

Remember to handle errors, add validation, and secure your application for production use.

This is a simplified example to get you started. In a production environment, you should also consider using DTOs (Data Transfer Objects) for input and output, implementing proper error handling, and securing your API.






