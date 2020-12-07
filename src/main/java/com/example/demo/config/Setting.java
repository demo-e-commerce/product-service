package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class Setting {
    @Value(value = "${kafka.bootstrapAddress:localhost:9092}")
    private String bootstrapAddress;

    @Value(value = "${kafka.auditTopicName:user-event-audit}")
    private String userEventAuditTopic;
}
