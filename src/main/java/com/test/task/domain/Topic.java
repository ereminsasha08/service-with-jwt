package com.test.task.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Topic {
    String titleTopic;

}
