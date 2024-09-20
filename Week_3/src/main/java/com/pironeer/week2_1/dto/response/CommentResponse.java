package com.pironeer.week2_1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

//@Builder
//public class CommentResponse {
//  @Schema(description = "댓글 ID", example = "1")
//  private Long id;
//
//  @Schema(description = "댓글 내용", example = "댓글입니다")
//  private String content;
//
//  @Schema(description = "댓글 생성 시간", example = "2024-10-10 10:10:00")
//  private LocalDateTime createdAt;
//
//  @Schema(description = "댓글 수정 시간", example = "2024-10-10 10:10:00")
//  private LocalDateTime updatedAt;
//
//  @Schema(description = "대댓글 리스트")
//  private List<CommentResponse> childComments;
//}

import com.pironeer.week2_1.repository.domain.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
    Long id, Long topicId, String content, LocalDateTime updatedAt) {

  public static CommentResponse of (Comment comment) {
    return new CommentResponse(comment.getId(), comment.getTopicId(), comment.getContent(), comment.getUpdatedAt());
  }
}