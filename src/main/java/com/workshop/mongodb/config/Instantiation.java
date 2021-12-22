package com.workshop.mongodb.config;

import com.workshop.mongodb.dto.CommentDTO;
import com.workshop.mongodb.domain.Post;
import com.workshop.mongodb.dto.AuthorDTO;
import com.workshop.mongodb.repository.PostRepository;
import com.workshop.mongodb.repository.UserRepository;
import com.workshop.mongodb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User alex = new User("Alex", "alex@example");
        User mario = new User("Mario", "mario@example");
        User ana = new User("Ana", "ana@example");

        userRepository.saveAll(Arrays.asList(alex, mario, ana));

        Post post = new Post(dateFormat.parse("21/03/2020"), "Partiu Viajar", "Vou viajar para Rio de Janeiro. Abraços!", new AuthorDTO(mario));
        Post post_2 = new Post(dateFormat.parse("23/05/2020"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(mario));

        CommentDTO commentDTO = new CommentDTO("Boa viajem!", dateFormat.parse("21/03/2020"), new AuthorDTO(alex));
        CommentDTO commentDTO_2 = new CommentDTO("Aproveite :)", dateFormat.parse("22/03/2020"), new AuthorDTO(ana));
        CommentDTO commentDTO_3 = new CommentDTO("Tenha um ótimo dia!", dateFormat.parse("23/03/2020"), new AuthorDTO(alex));

        post.getComments().addAll(Arrays.asList(commentDTO, commentDTO_2));
        post_2.getComments().addAll(List.of(commentDTO_3));

        postRepository.saveAll(Arrays.asList(post, post_2));

        mario.getPosts().addAll(Arrays.asList(post, post_2));
        userRepository.save(mario);
    }
}
