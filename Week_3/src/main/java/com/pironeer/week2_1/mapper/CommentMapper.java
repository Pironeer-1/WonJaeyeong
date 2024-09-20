package com.pironeer.week2_1.mapper;

import com.pironeer.week2_1.dto.request.CommentCreateRequest;
import com.pironeer.week2_1.dto.request.CommentUpdateRequest;
import com.pironeer.week2_1.repository.domain.Comment;

import java.time.LocalDateTime;

public class CommentMapper {
  public static Comment from(CommentCreateRequest request) {
    LocalDateTime now = LocalDateTime.now();
    return Comment.builder()
        .topicId(request.topicId())
        .content(request.content())
        .createdAt(now)
        .updatedAt(now)
        .build();
  }

  public static Comment from(CommentUpdateRequest request) {
    return Comment.builder()
        .id(request.id())
        .content(request.content())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
