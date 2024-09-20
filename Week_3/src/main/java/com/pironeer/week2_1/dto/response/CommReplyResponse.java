package com.pironeer.week2_1.dto.response;

import com.pironeer.week2_1.repository.domain.CommReply;

import java.time.LocalDateTime;

public record CommReplyResponse(Long id, Long commentId, String content, LocalDateTime updatedAt) {
  public static CommReplyResponse of (CommReply commReply) {
    return new CommReplyResponse(commReply.getId(), commReply.getCommentId(), commReply.getContent(), commReply.getUpdatedAt());
  }
}
