package pironeer3rd.SpringbootBoard.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardUpdateReq(
    @NotNull
    @Schema(description = "게시물 ID", example = "1")
    Long id,
    @NotBlank
    @Schema(description = "수정할 게시물 제목", example = "제목을 수정합니다")
    String title,
    @Schema(description = "수정할 게시물 내용", example = "내용을 수정합니다")
    String content
) {
}
