package com.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
