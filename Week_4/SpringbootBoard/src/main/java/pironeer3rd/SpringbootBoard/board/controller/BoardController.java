package pironeer3rd.SpringbootBoard.board.controller;

import com.sun.net.httpserver.Authenticator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pironeer3rd.SpringbootBoard.board.dto.request.BoardCreateReq;
import pironeer3rd.SpringbootBoard.board.dto.request.BoardUpdateReq;
import pironeer3rd.SpringbootBoard.board.dto.response.BoardResponse;
import pironeer3rd.SpringbootBoard.board.service.BoardService;
import pironeer3rd.SpringbootBoard.global.dto.response.SuccessResponse;
import pironeer3rd.SpringbootBoard.global.dto.response.result.ListResult;
import pironeer3rd.SpringbootBoard.global.dto.response.result.SingleResult;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시물(Board)")
@RequestMapping("/api/board")
public class BoardController {
  private final BoardService boardService;

  @PostMapping
  @Operation(summary = "게시물 작성")
  public SuccessResponse<SingleResult<Long>> create(
      @RequestAttribute("id") String userId,
      @Valid @RequestBody BoardCreateReq request
  ) {
    SingleResult<Long> save = boardService.save(request);
    return SuccessResponse.ok(save);
  }

  @GetMapping("/{boardId}")
  @Operation(summary = "게시물 단건 조회")
  public SuccessResponse<SingleResult<BoardResponse>> read(@PathVariable("boardId") Long id) {
    SingleResult<BoardResponse> result = boardService.findById(id);
    return SuccessResponse.ok(result);
  }

  @GetMapping
  @Operation(summary = "게시물 전체 조회")
  public SuccessResponse<ListResult<BoardResponse>> readAll() {
    ListResult<BoardResponse> result = boardService.findAll();
    return SuccessResponse.ok(result);
  }

  @PutMapping
  @Operation(summary = "게시물 수정")
  public ResponseEntity<BoardResponse> update(@Valid @RequestBody BoardUpdateReq request) {
    BoardResponse response = boardService.update(request);
    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/{boardId}")
  @Operation(summary = "게시물 삭제")
  public ResponseEntity<Long> remove(@PathVariable("boardId") Long id) {
    Long deletedId = boardService.deleteById(id);
    return ResponseEntity.ok().body(deletedId);
  }
}
