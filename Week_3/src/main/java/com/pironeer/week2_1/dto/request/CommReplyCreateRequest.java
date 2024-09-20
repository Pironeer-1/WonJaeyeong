package com.pironeer.week2_1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record CommReplyCreateRequest(
    @NotNull
    @Schema(description = "댓글 ID", example = "1")
    Long commentId,

    @NotBlank
    @Schema(description = "대댓글 내용", example = "이것은 대댓글입니다")
    String content) {
}
