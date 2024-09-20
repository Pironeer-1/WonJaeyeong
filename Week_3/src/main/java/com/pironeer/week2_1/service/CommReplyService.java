package com.pironeer.week2_1.service;

import com.pironeer.week2_1.dto.request.CommReplyCreateRequest;
import com.pironeer.week2_1.dto.request.CommentUpdateRequest;
import com.pironeer.week2_1.dto.response.CommReplyResponse;
import com.pironeer.week2_1.repository.CommReplyRepository;
import com.pironeer.week2_1.repository.CommentRepository;
import com.pironeer.week2_1.repository.domain.CommReply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommReplyService {
  private final CommReplyRepository comCommentRepository;
  private final CommentRepository commentRepository;

  public void save(CommReplyCreateRequest request) {
    if (commentRepository.findById(request.commentId()).isEmpty()) {
      throw new RuntimeException("댓글이 없습니다");
    }

    CommReply commReply = CommReply.builder()
        .commentId(request.commentId())
        .content(request.content())
        .updatedAt(LocalDateTime.now())
        .build();

    comCommentRepository.save(commReply);
  }

  public CommReplyResponse findById(Long id) {
    CommReply commReply = comCommentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("대댓글이 없습니다"));
    return CommReplyResponse.of(commReply);
  }

  public CommReplyResponse update(CommentUpdateRequest request) {
    CommReply commReply = comCommentRepository.findById(request.id())
        .orElseThrow(()-> new RuntimeException("대댓글이 없습니다"));

    commReply.setContent(request.content());
    commReply.setUpdatedAt(LocalDateTime.now());
    comCommentRepository.save(commReply);
    return CommReplyResponse.of(commReply);
  }

  public void deleteById(Long id) {
    comCommentRepository.deleteById(id);
  }

  public List<CommReplyResponse> findAll() {
    List<CommReply> comComments = comCommentRepository.findAll();
    return comComments.stream().map(CommReplyResponse::of).toList();
  }
}
