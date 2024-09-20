package com.pironeer.week2_1.controller;

import com.pironeer.week2_1.dto.request.CommentCreateRequest;
import com.pironeer.week2_1.dto.request.CommentUpdateRequest;
import com.pironeer.week2_1.dto.request.TopicUpdateRequest;
import com.pironeer.week2_1.dto.response.CommentResponse;
import com.pironeer.week2_1.dto.response.TopicResponse;
import com.pironeer.week2_1.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
  private final CommentService commentService;

  @PostMapping
  @Operation(summary = "댓글 달기")
  public ResponseEntity<?> create(@RequestBody CommentCreateRequest request) {
    commentService.save(request);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{commentId}")
  @Operation(summary = "댓글 조회")
  public ResponseEntity<CommentResponse> read(@PathVariable("commentId") Long id) {
    CommentResponse response = commentService.findById(id);
    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/{commentId}")
  @Operation(summary = "댓글 삭제")
  public ResponseEntity<?> delete(@PathVariable("commentId") Long id) {
    commentService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  @Operation(summary = "모든 댓글 조회")
  public ResponseEntity<List<CommentResponse>> readAll() {
    List<CommentResponse> responses = commentService.findAll();
    return ResponseEntity.ok().body(responses);
  }
}