package com.blog.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {
    
    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/index.html");
    }

    @GetMapping("/posts")
    public RedirectView posts() {
        return new RedirectView("/posts.html");
    }

    @GetMapping("/create-post")
    public RedirectView createPost() {
        return new RedirectView("/create-post.html");
    }
} 