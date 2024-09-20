package com.pironeer.week2_1.repository.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommReply {
  private Long id;
  private Long commentId; // Topic의 id와 연관되는 필드
  private String content;
  private LocalDateTime updatedAt;

  @Builder
  public CommReply(Long id, Long commentId, String content, LocalDateTime updatedAt) {
    this.id = id;
    this.commentId = commentId;
    this.content = content;
    this.updatedAt = updatedAt;
  }
}
