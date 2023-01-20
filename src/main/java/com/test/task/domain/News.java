package com.test.task.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;


@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
public class News extends AbstractBaseEntity{
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Source source;
    @Embedded
    private Topic topic;
}
