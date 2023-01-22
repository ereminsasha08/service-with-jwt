package com.test.task.service.source;

import com.test.task.domain.news.Source;
import com.test.task.repository.source.SourceRepository;
import com.test.task.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceServiceImp implements SourceService {

    final private SourceRepository sourceRepository;

    @Override
    public List<Source> findAll() {
        return sourceRepository.findAll();
    }
}
