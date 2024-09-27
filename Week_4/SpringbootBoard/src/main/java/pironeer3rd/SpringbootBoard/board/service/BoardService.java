package pironeer3rd.SpringbootBoard.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pironeer3rd.SpringbootBoard.board.dto.request.BoardCreateReq;
import pironeer3rd.SpringbootBoard.board.dto.request.BoardUpdateReq;
import pironeer3rd.SpringbootBoard.board.dto.response.BoardResponse;
import pironeer3rd.SpringbootBoard.board.entity.Board;
import pironeer3rd.SpringbootBoard.board.mapper.BoardMapper;
import pironeer3rd.SpringbootBoard.board.repository.BoardRepository;
import pironeer3rd.SpringbootBoard.global.dto.response.result.ListResult;
import pironeer3rd.SpringbootBoard.global.dto.response.result.SingleResult;
import pironeer3rd.SpringbootBoard.global.exception.CustomException;
import pironeer3rd.SpringbootBoard.global.exception.ErrorCode;
import pironeer3rd.SpringbootBoard.global.service.ResponseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;

  public SingleResult<Long> save(BoardCreateReq request) {
    Board savedBoard = boardRepository.save(BoardMapper.from(request));
    return ResponseService.getSingleResult(savedBoard.getId());
  }

  public SingleResult<BoardResponse> findById(Long id) {
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    BoardResponse boardResponse = BoardResponse.of(board);
    return ResponseService.getSingleResult(boardResponse);
  }

  public ListResult<BoardResponse> findAll() {
    List<Board> boards = boardRepository.findAll();
    List<BoardResponse> list = boards.stream().map(BoardResponse::of).toList();
    return ResponseService.getListResult(list);
  }

  public BoardResponse update(BoardUpdateReq request) {
    Board board = boardRepository.findById(request.id())
        .orElseThrow(() -> new RuntimeException("TOPIC NOT FOUND"));
    boardRepository.save(board.update(request));
    return BoardResponse.of(board);
  }

  public Long deleteById(Long id) {
    Long deleteId = boardRepository.deleteById(id);
    return deleteId;
  }
}
