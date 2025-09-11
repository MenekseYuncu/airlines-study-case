package com.menekse.airlines.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BaseResponse<T> {

    public static final BaseResponse<Void> SUCCESS = createSuccess();

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    private HttpStatus httpStatus;

    private Boolean isSuccess;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;

    public static <T> BaseResponse<T> successOf(final T response) {
        return BaseResponse.<T>builder()
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .response(response).build();
    }

    private static BaseResponse<Void> createSuccess() {
        return BaseResponse.<Void>builder()
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .build();
    }
}