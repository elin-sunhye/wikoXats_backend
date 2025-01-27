package com.wiko.wikoxats_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto<T> {
    private int status;
    private String message;
    private T body;

    public static <T> RequestDto<T> success(T body) {
        return new RequestDto<>(200, "success", body);
    }

    public static <T> RequestDto<T> fail(T body) {
        return new RequestDto<>(400, "fail", body);
    }

    public static <T> RequestDto<T> forbidden(T body) {
        return new RequestDto<>(403, "Forbidden", body);
    }
}
