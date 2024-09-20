package com.pironeer.week2_1.repository;

import com.pironeer.week2_1.repository.domain.CommReply;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CommReplyRepository {
  private final AtomicLong commReplyIdxGenerator = new AtomicLong(0);
  private final Map<Long, CommReply> commReplyMap =  new HashMap<>();

  public void save(CommReply commReply) {
    if(commReply.getId() == null) {
      Long id = commReplyIdxGenerator.incrementAndGet();
      commReply.setId(id);
      commReplyMap.put(id, commReply);
    } else {
      commReplyMap.replace(commReply.getId(), commReply);
    }
  }

  public Optional<CommReply> findById(Long id) {
    return Optional.of(commReplyMap.get(id));
  }

  public void deleteById(Long id) {
    Assert.notNull(id, "ID는 공란일 수 없습니다.");
    commReplyMap.remove(id);
  }

  public List<CommReply> findAll() {
    return commReplyMap.values().stream().toList();
  }

  public void deleteAllByCommentId(Long commentId) {
    Assert.notNull(commentId, "Comment ID MUST NOT BE NULL");

    Iterator<Map.Entry<Long, CommReply>> iterator = commReplyMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Long, CommReply> entry = iterator.next();
      if (entry.getValue().getCommentId().equals(commentId)) {
        iterator.remove();
      }
    }
  }
}
