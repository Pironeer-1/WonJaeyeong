package pironeer3rd.SpringbootBoard.global.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pironeer3rd.SpringbootBoard.global.dto.response.JwtTokenSet;
import pironeer3rd.SpringbootBoard.global.jwt.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtUtil jwtUtil;

  public JwtTokenSet generateToken(Long userIdx) {
    String token = jwtUtil.createToken(userIdx);

    JwtTokenSet jwtTokenSet = JwtTokenSet.builder()
        .token(token)
        .build();

    return jwtTokenSet;
  }
}