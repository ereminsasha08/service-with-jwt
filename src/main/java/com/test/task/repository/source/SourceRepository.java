package com.test.task.repository.source;

import com.test.task.domain.news.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<Source, Integer> {
}