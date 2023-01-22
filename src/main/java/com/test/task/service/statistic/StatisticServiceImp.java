package com.test.task.service.statistic;

import com.test.task.repository.news.NewsRepositoryBackEndFilter;
import com.test.task.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StatisticServiceImp implements StatisticService {

    private final NewsRepositoryBackEndFilter newsRepository;
    @Override
    public List<NewsRepositoryBackEndFilter.TopicCount> countTotalTopicBySource(String sourceName) {
        return newsRepository.countTotalTopicBySource(sourceName);
    }
}
