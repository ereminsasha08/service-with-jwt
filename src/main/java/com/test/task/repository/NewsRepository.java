package com.test.task.repository;

import com.test.task.domain.News;
import com.test.task.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, String> {

    Page<News> findAll(Pageable pageable);
    @Query("SELECT n from News n where n.source.name=:source")
    Page<News> findAllBySource(String source, Pageable pageable);

    @Query("SELECT n from News n where n.topic.titleTopic=:topic")
    Page<News> findAllByTopic(String topic, Pageable pageable);

    @Query("SELECT DISTINCT n.topic FROM News n")
    List<Topic> getAllTopics();

    @Query("SELECT n.topic.titleTopic AS newsTopic, COUNT(n.topic) AS totalTopic "
            + "FROM News n WHERE n.source.name=:sourceName GROUP BY n.topic")
    List<TopicCount> countTotalTopicBySource(String sourceName);

    interface TopicCount {
        String getNewsTopic();

        Integer getTotalTopic();
    }
}
