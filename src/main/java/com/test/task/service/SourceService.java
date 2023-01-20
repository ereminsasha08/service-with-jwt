package com.test.task.service;

import com.test.task.domain.Source;
import com.test.task.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {

    @Autowired
    private SourceRepository sourceRepository;

    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

    public void save(Source source){
        sourceRepository.save(source);
    }
}
