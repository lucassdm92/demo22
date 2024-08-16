package com.br.jfoodservices.jfood.model.data.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@JsonSerialize
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T> implements Serializable {

    private HttpStatus apiResponseCode;
    private String apiResponseMessage;
    private T apiResponseContent;

}
