package com.rest.jwt_auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MessageResponse extends BaseResponse {

    private String message;
    private Object object;

    public MessageResponse(HttpStatus status, String message, Object object) {
        super(status.value());
        this.message = message;
        this.object = object;
    }
}
