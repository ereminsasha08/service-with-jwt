package com.test.task.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;



@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
public class News {
    @Id
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;
    @NotNull
    @Column(name = "source", nullable = false)
    private String source;
    @NotNull
    @Column(name = "topic", nullable = false)
    private String topic;
}
