package com.test.task.controller;

import com.test.task.repository.NewsRepository;
import com.test.task.domain.News;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/api/news")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'UNAUTHORIZED')")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping()
    public ResponseEntity<List<News>> getNews(@Nullable @RequestParam String filter, @Nullable @RequestParam String value) {
        List<News> news = newsRepository.findAll();
        if ("topic".equalsIgnoreCase(filter) && Objects.nonNull(value)) {
            news = news.stream().filter(m -> value.equalsIgnoreCase(m.getTopic())).toList();
        }
        if ("source".equalsIgnoreCase(filter) && Objects.nonNull(value)) {
            news = news.stream().filter(m -> value.equalsIgnoreCase(m.getSource())).toList();
        }
        return ResponseEntity.ok(news);
    }

    @GetMapping("/source")
    public ResponseEntity<List<String>> getAllSource() {
        List<String> sources = newsRepository.findAll().stream().map(n -> n.getSource()).distinct().toList();
        return ResponseEntity.ok(sources);
    }

    @GetMapping("/topic")
    public ResponseEntity<List<String>> getAllTopic() {
        List<String> sources = newsRepository.findAll().stream().map(n -> n.getTopic()).distinct().toList();
        return ResponseEntity.ok(sources);
    }


}