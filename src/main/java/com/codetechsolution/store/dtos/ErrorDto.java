package com.codetechsolution.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorDto {
    private String error;
    public ErrorDto(String error){
        this.error = error;
    }
}
