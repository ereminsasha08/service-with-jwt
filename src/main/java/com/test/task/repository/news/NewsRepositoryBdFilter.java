package com.test.task.repository.news;

import com.test.task.domain.news.News;
import com.test.task.domain.news.Topic;
import com.test.task.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepositoryBdFilter extends NewsRepository {
    @Override
    @Query("SELECT n from News n where n.source.name like :source")
    Page<News> findAllBySource(String source, Pageable pageable);

    @Override
    @Query("SELECT n from News n where n.topic.titleTopic like :topic")
    Page<News> findAllByTopic(String topic, Pageable pageable);


    @Query("SELECT DISTINCT n.topic FROM News n ORDER BY n.topic.titleTopic")
    List<Topic> getTopics();

}
