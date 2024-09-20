package com.pironeer.week2_1.dto.response;

import com.pironeer.week2_1.repository.domain.CommReply;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommReplyResponse(
    @Schema(description = "대댓글 ID", example = "1")
    Long id,

    @Schema(description = "댓글 ID", example = "1")
    Long commentId,

    @Schema(description = "대댓글 내용", example = "이것은 대댓글입니다")
    String content,

    @Schema(description = "대댓글 수정 시간", example = "2024-10-10 10:10:00")
    LocalDateTime updatedAt) {

  public static CommReplyResponse of(CommReply commReply) {
    return CommReplyResponse.builder()
        .id(commReply.getId())
        .commentId(commReply.getCommentId())
        .content(commReply.getContent())
        .updatedAt(commReply.getUpdatedAt())
        .build();
  }
}
