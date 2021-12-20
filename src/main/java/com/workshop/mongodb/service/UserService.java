package com.workshop.mongodb.service;

import com.workshop.mongodb.dto.UserDTO;
import com.workshop.mongodb.repository.UserRepository;
import com.workshop.mongodb.domain.User;
import com.workshop.mongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Object not found: Id ")
                .append(id).toString()));
    }

    public User insert(User user){
        return userRepository.insert(user);
    }

    public void deleteById(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User user){
        User newUser = findById(user.getId());
        updateData(newUser, user);
        return userRepository.save(user);
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }
}
