package com.test.task.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "source")
@NoArgsConstructor
public class Source extends AbstractNamedEntity {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<News> news;
}
