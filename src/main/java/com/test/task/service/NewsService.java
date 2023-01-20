package com.test.task.service;

import com.test.task.domain.News;
import com.test.task.domain.Topic;
import com.test.task.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public void save(News news) {
        newsRepository.save(news);
    }

    public List<Topic> getAllTopics() {
        return newsRepository.getAllTopics();
    }

    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public Page<News> findAllByTopic(String topicTitle, Pageable pageable) {
        return newsRepository.findAllByTopic(topicTitle, pageable);
    }

    public Page<News> findAllBySource(String sourceName, Pageable pageable) {
        return newsRepository.findAllBySource(sourceName, pageable);
    }

    public List<NewsRepository.TopicCount> countTotalTopicBySource(String sourceName) {
        return newsRepository.countTotalTopicBySource(sourceName);
    }
}
