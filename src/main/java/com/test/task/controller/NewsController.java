package com.test.task.controller;

import com.test.task.domain.News;
import com.test.task.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<News>> getNews(@RequestParam(required = false, defaultValue = "") String filter,
                                              @RequestParam(required = false, defaultValue = "") String value,
                                              @PageableDefault(sort = "title") Pageable pageable) {
        Page<News> newsPage = null;
        if ("topic".equalsIgnoreCase(filter) && Objects.nonNull(value)) {
            newsPage = newsRepository.findAllByTopic(value, pageable);
        }
        if ("source".equalsIgnoreCase(filter) && Objects.nonNull(value)) {
            newsPage = newsRepository.findAllBySource(value, pageable);
        }
        if (filter.isBlank()) {
            newsPage = newsRepository.findAll(pageable);
        }
        return ResponseEntity.ok(newsPage);
    }

    @GetMapping("/sources")
    public ResponseEntity<List<String>> getAllSource() {
        List<String> allSources = newsRepository.getAllSources();
        return ResponseEntity.ok(allSources);
    }

    @GetMapping("/topics")
    public ResponseEntity<List<String>> getAllTopic() {
        List<String> allTopics = newsRepository.getAllTopics();
        return ResponseEntity.ok(allTopics);
    }


}