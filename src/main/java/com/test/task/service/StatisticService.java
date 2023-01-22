package com.test.task.service;

import com.test.task.repository.news.NewsRepositoryBackEndFilter;

import java.util.List;

public interface StatisticService {
    List<NewsRepositoryBackEndFilter.TopicCount> countTotalTopicBySource(String sourceName);

}
