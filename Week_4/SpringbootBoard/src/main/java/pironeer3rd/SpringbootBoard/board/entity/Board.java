package pironeer3rd.SpringbootBoard.board.entity;

import lombok.Builder;
import lombok.Data;
import pironeer3rd.SpringbootBoard.board.dto.request.BoardUpdateReq;

import java.time.LocalDateTime;

@Data
public class Board {
  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  public Board(
      Long id,
      String title,
      String content,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Board update(BoardUpdateReq request) {
    this.title = request.title();
    this.content = request.content();
    this.updatedAt = LocalDateTime.now();
    return this;
  }
}
