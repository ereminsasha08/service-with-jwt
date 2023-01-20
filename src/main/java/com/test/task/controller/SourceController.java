package com.test.task.controller;

import com.test.task.domain.Source;
import com.test.task.repository.SourceRepository;
import com.test.task.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/sources")
@RequiredArgsConstructor
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @GetMapping
    public ResponseEntity<List<Source>> getAllSource() {
        List<Source> allSources = sourceService.getSources();
        return ResponseEntity.ok(allSources);
    }
}
