package com.currency.currencyexchangeapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessage implements Serializable {
    private Integer status;
    private String message;
    private String data;
}
