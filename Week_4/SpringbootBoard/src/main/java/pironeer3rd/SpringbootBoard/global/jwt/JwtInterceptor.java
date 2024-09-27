package pironeer3rd.SpringbootBoard.global.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import pironeer3rd.SpringbootBoard.global.exception.CustomException;
import pironeer3rd.SpringbootBoard.global.exception.ErrorCode;

@Component
public class JwtInterceptor implements HandlerInterceptor {

  private final JwtUtil jwtUtil;

  public JwtInterceptor(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("Authorization");
    if (token != null && token.startsWith("Bearer ")) {
      String jwtToken = token.substring(7);
      jwtUtil.verify(jwtToken);

      Long id = jwtUtil.getId(jwtToken);
      request.setAttribute("id", id);
      return true;
    }
    throw new CustomException(ErrorCode.UNAUTHORIZED);
  }
}
