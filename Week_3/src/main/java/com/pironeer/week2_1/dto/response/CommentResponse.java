package com.pironeer.week2_1.dto.response;

import com.pironeer.week2_1.repository.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CommentResponse(
    @Schema(description = "댓글 ID", example = "1")
    Long id,

    @Schema(description = "게시물 ID", example = "1")
    Long topicId,

    @Schema(description = "댓글 내용", example = "댓글입니다")
    String content,

    @Schema(description = "댓글 생성 시간", example = "2024-10-10 10:10:00")
    LocalDateTime createdAt,

    @Schema(description = "댓글 수정 시간", example = "2024-10-10 10:10:00")
    LocalDateTime updatedAt,

    @Schema(description = "대댓글 리스트")
    List<CommReplyResponse> childComments) {

  public static CommentResponse of(Comment comment) {
    return CommentResponse.builder()
        .id(comment.getId())
        .topicId(comment.getTopicId())
        .content(comment.getContent())
        .createdAt(comment.getCreatedAt())
        .updatedAt(comment.getUpdatedAt())
        .childComments(List.of()) // 초기값으로 빈 리스트 설정
        .build();
  }
}
