package com.blog.blog.controller;
import com.blog.blog.model.Post;
import com.blog.blog.repository.PostRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        // Create a new Post instance with only title and content
        Post newPost = new Post(post.getTitle(), post.getContent());
        return postRepository.save(newPost);
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}


