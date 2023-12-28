package com.swyg.picketbackend.board.dto.res.board;


import com.swyg.picketbackend.board.Entity.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
@Builder
public class GetBoardListResponseDTO {

    @Schema(description = "버킷 번호", example = "1")
    private Long boardId;  // 게시글 번호

    @Schema(description = "버킷 제목", example = "버킷 제목")
    private String title; // 게시글 제목

    @Schema(description = "버킷 작성자 닉네임", example = "닉네임")
    private String nickname; // 작성자 닉네임

    @Schema(description = "마감기한", example = "2023-12-30")
    private LocalDate deadline; // 마감 기한

    @Schema(description = "좋아요 개수", example = "10")
    private Long likeCount; // 마감 기한

    @Schema(description = "스크랩 개수", example = "10")
    private Long ScrapCount; // 마감 기한

    @Schema(description = "파일 이름", example = "S3 파일 이름")
    private String filename; // 파일 이름

    @Schema(description = "파일 경로", example = "S3 이미지 경로")
    private String filepath; // 파일 경로


    // entityList -> dtoList
    public static Slice<GetBoardListResponseDTO> toDTOList(Slice<Board> boardList) {
        List<GetBoardListResponseDTO> dtoList = boardList.stream()
                .map(GetBoardListResponseDTO::toDTO)
                .toList();

        return new SliceImpl<>(dtoList, boardList.getPageable(), boardList.hasNext());
    }

    // entity -> dto
    public static GetBoardListResponseDTO toDTO(Board board) {
        return GetBoardListResponseDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .nickname(board.getMember().getNickname())
                .deadline(board.getDeadline())
                .likeCount((long) board.getHeart().size())
                .ScrapCount((long) board.getScrap().size())
                .filename(board.getFilename())
                .filepath(board.getFilepath())
                .build();
    }

}
