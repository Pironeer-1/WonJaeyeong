package pironeer3rd.SpringbootBoard.member.mapper;

import pironeer3rd.SpringbootBoard.member.dto.request.MemberCreateReq;
import pironeer3rd.SpringbootBoard.member.entity.Member;

public class MemberMapper {
  public static Member from(MemberCreateReq request) {
    return Member.builder()
        .memberId(request.memberId())
        .password(request.password())
        .name(request.name())
        .build();
  }
}
