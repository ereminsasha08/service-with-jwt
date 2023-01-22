package com.test.task.controller;

import com.test.task.domain.news.Topic;
import com.test.task.dto.NewsDto;
import com.test.task.service.NewsService;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class NewsController {

    final private NewsService newsService;

    public NewsController(@Qualifier(value = "newsServiceBdFilter") NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping()
    public ResponseEntity<List<NewsDto>> getNews(@PageableDefault(sort = "title") Pageable pageable) {
        List<NewsDto> newsPage = newsService.findAll(pageable);
        return ResponseEntity.ok(newsPage);
    }


    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getTopics(@PageableDefault(sort = "title") Pageable pageable) {
        List<Topic> allTopics = newsService.getTopics(pageable);
        return ResponseEntity.ok(allTopics);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<NewsDto>> filterNews(@RequestParam(required = false, defaultValue = "") String filter,
                                                    @RequestParam(required = false, defaultValue = "") String value,
                                                    @PageableDefault(sort = "title") Pageable pageable) {
        List<NewsDto> newsFiltered = newsService.filter(filter, value, pageable);
        return ResponseEntity.ok(newsFiltered);

    }

}