package com.test.task.service;

import com.test.task.domain.News;
import com.test.task.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public void save(News news){
        newsRepository.save(news);
    }
}
