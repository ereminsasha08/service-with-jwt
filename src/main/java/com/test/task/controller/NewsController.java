package com.test.task.controller;

import com.test.task.domain.News;
import com.test.task.domain.Topic;
import com.test.task.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

@RestController()
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    @Autowired
    private NewsService newsService;

    private Map<String, BiFunction<String, Pageable, Page>> filters = new HashMap<>();

    {
        filters.put("topic", (value, pageable) -> newsService.findAllByTopic(value, pageable));
        filters.put("source", (value, pageable) -> newsService.findAllBySource(value, pageable));
    }

    @GetMapping()
    public ResponseEntity<Page<News>> getNews(@PageableDefault(sort = "title") Pageable pageable) {
        Page<News> newsPage = newsService.findAll(pageable);
        return ResponseEntity.ok(newsPage);
    }


    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopic() {
        List<Topic> allTopics = newsService.getAllTopics();
        return ResponseEntity.ok(allTopics);
    }

    @GetMapping("/filter")
    public Page<News> filterNews(@RequestParam(required = false, defaultValue = "") String filter,
                                 @RequestParam(required = false, defaultValue = "") String value,
                                 @PageableDefault(sort = "title") Pageable pageable) {
        BiFunction<String, Pageable, Page> functionFilter = filters.get(filter.toLowerCase());
        if (Objects.nonNull(functionFilter)) {
            return functionFilter.apply(value, pageable);
        }
        return null;
    }
}