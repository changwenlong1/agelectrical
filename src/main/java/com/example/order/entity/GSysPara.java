package com.example.order.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysPara implements Serializable {

    private Long paraId;

    private Long userId;

    private String keyss;

    private String value;

    private String remark;

    private String isvalid;

    private static final long serialVersionUID = 1L;


}