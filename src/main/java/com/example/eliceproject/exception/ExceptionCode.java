package com.example.eliceproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {

    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다."),
    BOARD_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 게시판입니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시물입니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다.");

    @Getter
    private HttpStatus status;
    private String HttpMessage;

    ExceptionCode(HttpStatus status, String HttpMessage) {
        this.status = status;
        this.HttpMessage = HttpMessage;
    }

    public String getMessage() {
        return HttpMessage;
    }
}
