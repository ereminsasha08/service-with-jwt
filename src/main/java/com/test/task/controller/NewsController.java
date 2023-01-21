package com.test.task.controller;

import com.test.task.domain.news.Topic;
import com.test.task.dto.NewsDto;
import com.test.task.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    final private NewsService newsService;

    @GetMapping()
    public ResponseEntity<Page<NewsDto>> getNews(@PageableDefault(sort = "title") Pageable pageable) {
        Page<NewsDto> newsPage = newsService.findAll(pageable);
        return ResponseEntity.ok(newsPage);
    }


    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getTopics(@PageableDefault(sort = "title") Pageable pageable) {
        List<Topic> allTopics = newsService.getTopics(pageable);
        return ResponseEntity.ok(allTopics);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<NewsDto>> filterNews(@RequestParam(required = false, defaultValue = "") String filter,
                                                    @RequestParam(required = false, defaultValue = "") String value,
                                                    @PageableDefault(sort = "title") Pageable pageable) {
        Page<NewsDto> newsFiltered = newsService.filter(filter, value, pageable);
        return ResponseEntity.ok(newsFiltered);

    }

}