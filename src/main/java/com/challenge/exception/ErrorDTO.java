package com.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO implements Serializable {

    private Integer status;
    private Long timeStamp;
    private String msg;

}
