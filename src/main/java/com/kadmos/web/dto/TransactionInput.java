package com.kadmos.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInput {

    @NotNull
    @Positive
    private Double amount;
    @NotNull
    private Long from;
    @NotNull
    private Long to;

}
