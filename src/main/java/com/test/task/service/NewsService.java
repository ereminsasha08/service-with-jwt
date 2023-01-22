package com.test.task.service;

import com.test.task.domain.news.News;
import com.test.task.domain.news.Topic;
import com.test.task.dto.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    List<NewsDto> filter(String filter, String value, Pageable pageable);

    List<NewsDto> findAll(Pageable pageable);

    List<Topic> getTopics(Pageable pageable);

    default List<NewsDto> convertPageNewsToListNewsDto(Page<News> newsPage){
        return newsPage.stream()
                .parallel()
                .map(NewsDto::new)
                .toList();
    }

}
