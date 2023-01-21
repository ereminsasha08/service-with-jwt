package com.test.task.service;

import com.test.task.domain.news.Source;
import com.test.task.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceService {


    final private SourceRepository sourceRepository;

    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

}
