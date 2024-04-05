package com.prasiddha.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private T data;
    private String errorMessage;

    public ResponseDTO(T data)
    {
        this.data = data;
    }
    public ResponseDTO(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

}
