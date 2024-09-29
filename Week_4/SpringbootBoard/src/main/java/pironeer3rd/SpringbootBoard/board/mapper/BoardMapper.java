package pironeer3rd.SpringbootBoard.board.mapper;

import pironeer3rd.SpringbootBoard.board.dto.request.BoardCreateReq;
import pironeer3rd.SpringbootBoard.board.entity.Board;

import java.time.LocalDateTime;

public class BoardMapper {
  public static Board from(BoardCreateReq request) {
    LocalDateTime now = LocalDateTime.now();
    return Board.builder()
        .title(request.title())
        .content(request.content())
        .createdAt(now)
        .updatedAt(now)
        .build();
  }
}
