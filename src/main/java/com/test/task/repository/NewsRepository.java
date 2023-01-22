package com.test.task.repository;

import com.test.task.domain.news.News;
import com.test.task.domain.news.Topic;
import com.test.task.exception.NotSupportedMethodRuntimeException;
import com.test.task.repository.news.NewsRepositoryBackEndFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, String> {

    default Page<News> findAllBySource(String s, Pageable pageable) {
        throw new NotSupportedMethodRuntimeException();
    }

    default Page<News> findAllByTopic(String s, Pageable pageable) {
        throw new NotSupportedMethodRuntimeException();
    }

    default List<Topic> getTopics() {
        throw new NotSupportedMethodRuntimeException();
    }
    @Query("SELECT n.topic.titleTopic AS newsTopic, COUNT(n.topic) AS totalTopic "
            + "FROM News n WHERE n.source.name=:sourceName GROUP BY n.topic")
    List<NewsRepositoryBackEndFilter.TopicCount> countTotalTopicBySource(String sourceName);

    interface TopicCount {
        String getNewsTopic();

        Integer getTotalTopic();
    }
}
