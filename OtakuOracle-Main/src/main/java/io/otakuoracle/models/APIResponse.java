package io.otakuoracle.models;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class APIResponse<T> {
    private HttpStatus status;
    private boolean success;
    private T message;
}
