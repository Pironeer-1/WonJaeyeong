package pironeer3rd.SpringbootBoard.member.repository;

import org.springframework.stereotype.Repository;
import pironeer3rd.SpringbootBoard.member.entity.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepository {
  private final AtomicLong memberIdxGenerator = new AtomicLong(0);
  private final Map<Long, Member> memberMap = new HashMap<>();

  public Member save(Member member) {
    if (member.getMemberId() == null) {
      Long id = memberIdxGenerator.incrementAndGet();
      member.setId(id);
      memberMap.put(id, member);
      return member;
    } else {
      memberMap.replace(member.getId(), member);
      return member;
    }
  }

  public Optional<Member> findByMemberId(String memberId) {
    return memberMap.values().stream()
        .filter(data -> data.getMemberId().equals(memberId))
        .findAny();
  }

  public Boolean existByMemberId(String memberId) {
    return memberMap.values().stream()
        .anyMatch(data -> data.getMemberId().equals(memberId));
  }
}
