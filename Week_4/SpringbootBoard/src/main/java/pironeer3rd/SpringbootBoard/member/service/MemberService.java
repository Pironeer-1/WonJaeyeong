package pironeer3rd.SpringbootBoard.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pironeer3rd.SpringbootBoard.global.dto.response.JwtTokenSet;
import pironeer3rd.SpringbootBoard.global.dto.response.result.SingleResult;
import pironeer3rd.SpringbootBoard.global.exception.CustomException;
import pironeer3rd.SpringbootBoard.global.exception.ErrorCode;
import pironeer3rd.SpringbootBoard.global.service.AuthService;
import pironeer3rd.SpringbootBoard.global.service.ResponseService;
import pironeer3rd.SpringbootBoard.member.dto.request.MemberCreateReq;
import pironeer3rd.SpringbootBoard.member.dto.request.MemberLoginReq;
import pironeer3rd.SpringbootBoard.member.entity.Member;
import pironeer3rd.SpringbootBoard.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final AuthService authService;

  public SingleResult<JwtTokenSet> register(MemberCreateReq req) {
    if (memberRepository.existByMemberId(req.memberId())) {
      throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
    }

    Member newMember = Member.builder()
        .memberId(req.memberId())
        .password(req.password())
        .name(req.name())
        .build();
    newMember = memberRepository.save(newMember);

    JwtTokenSet jwtTokenSet = authService.generateToken(newMember.getId());

    return ResponseService.getSingleResult(jwtTokenSet);
  }

  public SingleResult<JwtTokenSet> login(MemberLoginReq req) {
    Member member = memberRepository.findByMemberId(req.memberId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));

    if (!member.getPassword().equals(req.password())) {
      throw new CustomException(ErrorCode.USER_WRONG_PASSWORD);
    }

    JwtTokenSet jwtTokenSet = authService.generateToken(member.getId());

    return ResponseService.getSingleResult(jwtTokenSet);
  }
}
