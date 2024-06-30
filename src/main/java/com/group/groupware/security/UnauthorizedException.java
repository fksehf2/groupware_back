package com.group.groupware.security;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("해당하는 회원이 없습니다.\n다시 시도해해주세요.");
    }

}
