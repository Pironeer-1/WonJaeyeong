package pironeer3rd.SpringbootBoard.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import pironeer3rd.SpringbootBoard.member.entity.Member;

@Builder
public record MemberResponse(
    @NotNull
    @Schema(description = "회원 ID", example = "1")
    Long id,
    @NotBlank
    @Schema(description = "회원 아이디", example = "user123")
    String memberId,
    @NotBlank
    @Schema(description = "회원 이름", example = "홍길동")
    String name
) {
  public static MemberResponse of(Member member) {
    return MemberResponse.builder()
        .id(member.getId())
        .memberId(member.getMemberId())
        .name(member.getName())
        .build();
  }
}
