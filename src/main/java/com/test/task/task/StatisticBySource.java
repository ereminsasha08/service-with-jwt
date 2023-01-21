package com.test.task.task;

import com.test.task.domain.news.Source;
import com.test.task.repository.NewsRepository;
import com.test.task.service.NewsService;
import com.test.task.service.SourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class StatisticBySource {

    @Autowired
    private SourceService sourceService;


    @Autowired
    private NewsService newsService;
    private static int sizeThreadPool = 4;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(sizeThreadPool);


    @Scheduled(cron = "${app.setting.cron-template}")
    public void saveStatistic() {
        List<Source> allSources = sourceService.findAll();
        for (Source source :
                allSources) {
            CompletableFuture.runAsync(new SaveSourceNewsAmount(source.getName()),
                    threadPool);
        }
    }

    private class SaveSourceNewsAmount implements Runnable {
        private final String sourceName;

        private SaveSourceNewsAmount(String sourceName) {
            this.sourceName = sourceName;
        }

        @Override
        public void run() {
            List<NewsRepository.TopicCount> topicCounts = newsService.countTotalTopicBySource(sourceName);

            Path sourceFile = Path.of("info_by_source/" + sourceName + ".csv");
            if (!Files.exists(sourceFile)) {
                try {
                    Files.createDirectory(Path.of("info_by_source"));
                    Files.createFile(sourceFile);
                } catch (IOException e) {
                    log.error("Error creating {} file", sourceFile);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            ;
            for (NewsRepository.TopicCount topic :
                    topicCounts) {
                stringBuilder.append(topic.getNewsTopic());
                stringBuilder.append(",");
                stringBuilder.append(topic.getTotalTopic());
                stringBuilder.append("\n");
            }
            try (FileOutputStream os = new FileOutputStream(sourceFile.toFile())) {
                os.write("Тема,Количество новостей\n".getBytes());
                os.write(stringBuilder.toString().getBytes());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
