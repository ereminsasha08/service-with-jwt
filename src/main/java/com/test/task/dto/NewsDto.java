package com.test.task.dto;

import com.test.task.domain.news.News;
import lombok.Getter;

@Getter

public class NewsDto {

    private String title;

    private String sourceName;

    private String topicTitle;

    public NewsDto(News news) {
        this.title = news.getTitle();
        this.sourceName = news.getSource().getName();
        this.topicTitle = news.getTopic().getTitleTopic();

    }
}
