package com.test.task.task;

import com.test.task.repository.NewsRepository;
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
    private static int sizeThreadPool = 4;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(sizeThreadPool);

    @Autowired
    private NewsRepository newsRepository;

    @Scheduled(cron = "${app.setting.cron-template}")
    public void saveStatistic() {
        List<String> allSources = newsRepository.getAllSources();
        for (String source :
                allSources) {
            CompletableFuture.runAsync(new SaveSourceNewsAmount(source),
                    threadPool);
        }
    }

    private class SaveSourceNewsAmount implements Runnable {
        private final String source;

        private SaveSourceNewsAmount(String source) {
            this.source = source;
        }

        @Override
        public void run() {
            List<NewsRepository.TopicCount> topicCounts = newsRepository.countTotalTopicBySource(source);

            Path sourceFile = Path.of("info_by_source/" + source + ".csv");
            if (!Files.exists(sourceFile)) {
                try {
                    Files.createDirectory(Path.of("info_by_source"));
                    Files.createFile(sourceFile);
                } catch (IOException e) {
                    log.error("Error creating {} file", sourceFile);
                }
            }
            StringBuilder stringBuilder;
            try (FileOutputStream os = new FileOutputStream(sourceFile.toFile())) {
                os.write("Тема,Количество новостей\n".getBytes());
                for (NewsRepository.TopicCount topic :
                        topicCounts) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(topic.getNewsTopic());
                    stringBuilder.append(",");
                    stringBuilder.append(topic.getTotalTopic());
                    stringBuilder.append("\n");
                    os.write(stringBuilder.toString().getBytes());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
