package com.pironeer.week2_1.repository.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class Comment {
  private Long id;
  private Long topicId;
  private String content;
  private LocalDateTime updatedAt;

  @Builder
  public Comment(Long id, Long topicId, String content, LocalDateTime updatedAt) {
    this.id = id;
    this.topicId = topicId;
    this.content = content;
    this.updatedAt = updatedAt;
  }
}
