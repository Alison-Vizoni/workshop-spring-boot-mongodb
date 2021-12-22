package com.workshop.mongodb.service;

import com.workshop.mongodb.domain.Post;
import com.workshop.mongodb.repository.PostRepository;
import com.workshop.mongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        Optional<Post> user = postRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Object not found: Id ")
                .append(id).toString()));
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitleContainingIgnoreCase(text);
    }
}
