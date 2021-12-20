package com.workshop.mongodb.config;

import com.workshop.mongodb.repository.UserRepository;
import com.workshop.mongodb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User alex = new User("Alex", "alex@example");
        User mario = new User("Mario", "mario@example");
        User ana = new User("Ana", "ana@example");

        userRepository.saveAll(Arrays.asList(alex, mario, ana));
    }
}
