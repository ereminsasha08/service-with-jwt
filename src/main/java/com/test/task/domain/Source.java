package com.test.task.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
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

    public Source(String name){
        this.name = name;
    }
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<News> news;
}
