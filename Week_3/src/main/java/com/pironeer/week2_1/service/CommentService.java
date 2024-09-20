package com.pironeer.week2_1.service;

import com.pironeer.week2_1.dto.request.CommentCreateRequest;
import com.pironeer.week2_1.dto.request.CommentUpdateRequest;
import com.pironeer.week2_1.dto.response.CommentResponse;
import com.pironeer.week2_1.repository.CommReplyRepository;
import com.pironeer.week2_1.repository.CommentRepository;
import com.pironeer.week2_1.repository.TopicRepository;
import com.pironeer.week2_1.repository.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final TopicRepository topicRepository;
  private final CommReplyRepository comCommentRepository;
  public void save(CommentCreateRequest request) {
    if (topicRepository.findById(request.topicId()).isEmpty()) {
      throw new RuntimeException("댓글이 없습니다");
    }

    Comment comment = Comment.builder()
        .topicId(request.topicId())
        .content(request.content())
        .updatedAt(LocalDateTime.now())
        .build();
    commentRepository.save(comment);
  }

  public CommentResponse findById(Long commentId) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(()-> new RuntimeException("댓글이 없습니다"));

    return CommentResponse.of(comment);
  }

  public CommentResponse update(CommentUpdateRequest request) {
    Comment comment = commentRepository.findById(request.id())
        .orElseThrow(()-> new RuntimeException("댓글이 없습니다"));

    comment.setContent(request.content());
    comment.setUpdatedAt(LocalDateTime.now());
    commentRepository.save(comment);
    return CommentResponse.of(comment);
  }

  public void deleteById(Long id) {
    commentRepository.deleteById(id);
  }

  public List<CommentResponse> findAll() {
    List<Comment> comments = commentRepository.findAll();
    return comments.stream().map(CommentResponse::of).toList();
  }
}
