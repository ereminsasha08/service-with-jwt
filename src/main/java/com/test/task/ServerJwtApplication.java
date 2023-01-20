package com.test.task;

import com.test.task.domain.News;
import com.test.task.domain.Source;
import com.test.task.domain.Topic;
import com.test.task.service.NewsService;
import com.test.task.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerJwtApplication implements ApplicationRunner {

    @Autowired
    private NewsService newsService;

    @Autowired
    private SourceService sourceService;


    public static void main(String[] args) {
        SpringApplication.run(ServerJwtApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Source source = new Source("source");
        sourceService.save(source);
        News news = new News("mew", source, new Topic("topic"));
        newsService.save(news);
    }
}
