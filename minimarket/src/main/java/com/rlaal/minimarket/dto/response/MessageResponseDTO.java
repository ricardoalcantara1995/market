package com.rlaal.minimarket.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MessageResponseDTO {
    private HttpStatus status;
    private String messaje;
}
