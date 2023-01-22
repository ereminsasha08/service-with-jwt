package com.test.task.service.news;

import com.test.task.domain.news.News;
import com.test.task.domain.news.Topic;
import com.test.task.dto.NewsDto;
import com.test.task.exception.NotFoundFilterException;
import com.test.task.repository.NewsRepository;
import com.test.task.repository.news.NewsRepositoryBackEndFilter;
import com.test.task.service.NewsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
public class NewsServiceBackEndFilter implements NewsService {

    final private NewsRepository newsRepository;

    public NewsServiceBackEndFilter(@Qualifier("newsRepositoryBackEndFilter") NewsRepositoryBackEndFilter newsRepository) {
        this.newsRepository = newsRepository;
    }

    private Map<String, BiFunction<String, Pageable, List<NewsDto>>> filters = new HashMap<>();

    {
        filters.put("topic", this::findAllByTopic);
        filters.put("source", this::findAllBySource);
    }


    public List<NewsDto> findAll(Pageable pageable) {
        return convertPageNewsToListNewsDto(newsRepository.findAll(pageable));

    }

    public List<NewsDto> filter(String filter, String value, Pageable pageable) {
        Optional<BiFunction<String, Pageable, List<NewsDto>>> functionFilter = Optional.ofNullable(filters.get(filter.toLowerCase()));
        List<NewsDto> filteredNews = functionFilter.orElseThrow(() -> new NotFoundFilterException(filter)).apply(value, pageable);
        return filteredNews;
    }

    private List<NewsDto> findAllByTopic(String topicTitle, Pageable pageable) {
        return findAll(pageable)
                .stream()
                .filter(news -> news.getTopicTitle().startsWith(topicTitle))
                .toList();
    }

    private List<NewsDto> findAllBySource(String sourceName, Pageable pageable) {
        return findAll(pageable)
                .stream()
                .filter(news -> news.getSourceName().startsWith(sourceName))
                .toList();
    }

    public List<Topic> getTopics(Pageable pageable) {
        return newsRepository.findAll(pageable)
                .stream()
                .parallel()
                .map(News::getTopic)
                .distinct()
                .toList();
    }



}
