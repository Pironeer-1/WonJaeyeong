package com.pironeer.week2_1.mapper;

import com.pironeer.week2_1.dto.request.CommReplyCreateRequest;
import com.pironeer.week2_1.repository.domain.CommReply;

import java.time.LocalDateTime;

public class CommReplyMapper {
  public static CommReply from(CommReplyCreateRequest request) {
    LocalDateTime now = LocalDateTime.now();
    return CommReply.builder()
        .commentId(request.commentId())
        .content(request.content())
        .updatedAt(now)
        .build();
  }
}
