package com.pironeer.week2_1.controller;

import com.pironeer.week2_1.dto.request.CommReplyCreateRequest;
import com.pironeer.week2_1.dto.request.CommentUpdateRequest;
import com.pironeer.week2_1.dto.response.CommReplyResponse;
import com.pironeer.week2_1.service.CommReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commReply")
public class CommReplyController {
  private final CommReplyService commReplyService;

  @PostMapping
  public ResponseEntity<?> create(@RequestBody CommReplyCreateRequest request) {
    commReplyService.save(request);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{commReplyId}")
  public ResponseEntity<CommReplyResponse> read(@PathVariable("commReplyId") Long id) {
    CommReplyResponse response = commReplyService.findById(id);
    return ResponseEntity.ok().body(response);
  }

  @PatchMapping
  public ResponseEntity<CommReplyResponse> update(@RequestBody CommentUpdateRequest request) {
    CommReplyResponse response = commReplyService.update(request);
    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/{commReplyId}")
  public ResponseEntity<?> delete(@PathVariable("commReplyId") Long id) {
    commReplyService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<CommReplyResponse>> readAll() {
    List<CommReplyResponse> responses = commReplyService.findAll();
    return ResponseEntity.ok().body(responses);
  }

}
