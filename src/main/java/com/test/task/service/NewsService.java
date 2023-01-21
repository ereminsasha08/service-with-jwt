package com.test.task.service;

import com.test.task.domain.news.News;
import com.test.task.domain.news.Topic;
import com.test.task.dto.NewsDto;
import com.test.task.exception.NotFoundFilterException;
import com.test.task.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    final private NewsRepository newsRepository;

    private Map<String, BiFunction<String, Pageable, Page<NewsDto>>> filters = new HashMap<>();

    {
        filters.put("topic", this::findAllByTopic);
        filters.put("source", this::findAllBySource);
    }

    public Page<NewsDto> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable).map(NewsDto::new);
    }

    public Page<NewsDto> filter(String filter, String value, Pageable pageable) {
        Optional<BiFunction<String, Pageable, Page<NewsDto>>> functionFilter = Optional.ofNullable(filters.get(filter.toLowerCase()));
        Page<NewsDto> filteredNews = functionFilter.orElseThrow(() -> new NotFoundFilterException(filter)).apply(value, pageable);
        return filteredNews;
    }

    public List<Topic> getTopics(Pageable pageable) {
        return newsRepository.findAll(pageable).stream()
                .parallel()
                .map(News::getTopic)
                .distinct()
                .collect(Collectors.toList());
    }
    public List<NewsRepository.TopicCount> countTotalTopicBySource(String sourceName) {
        return newsRepository.countTotalTopicBySource(sourceName);
    }

    private Page<NewsDto> findAllByTopic(String topicTitle, Pageable pageable) {
        return newsRepository.findAllByTopic(topicTitle + '%', pageable).map(NewsDto::new);
    }

    private Page<NewsDto> findAllBySource(String sourceName, Pageable pageable) {
        return newsRepository.findAllBySource(sourceName + '%', pageable).map(NewsDto::new);
    }


}
