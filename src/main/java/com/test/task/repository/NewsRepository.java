package com.test.task.repository;

import com.test.task.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, String> {

    Page<News> findAll(Pageable pageable);

    Page<News> findAllBySource(String source, Pageable pageable);

    Page<News> findAllByTopic(String topic, Pageable pageable);


    @Query("SELECT DISTINCT n.topic FROM News n")
    List<String> getAllTopics();

    @Query("SELECT DISTINCT n.source FROM News n")
    List<String> getAllSources();

    @Query("SELECT n.topic AS newsTopic, COUNT(n.topic) AS totalTopic "
            + "FROM News n WHERE n.source=:source GROUP BY n.topic")
    List<TopicCount> countTotalTopicBySource(String source);

    interface TopicCount {
        String getNewsTopic();

        Integer getTotalTopic();
    }
}
