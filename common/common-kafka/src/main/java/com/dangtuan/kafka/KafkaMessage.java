package com.dangtuan.kafka;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaMessage {

  private final String uuid = UUID.randomUUID().toString();
  private final Date createdDate = new Date();
  private KafkaEventType eventType = KafkaEventType.default_none;
  private Class clazz;
  private String data;
}
