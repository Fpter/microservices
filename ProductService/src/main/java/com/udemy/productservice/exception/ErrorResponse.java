package com.udemy.productservice.exception;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;
}
