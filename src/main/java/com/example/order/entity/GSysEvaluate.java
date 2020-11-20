package com.example.order.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysEvaluate implements Serializable {

    private Long evaluateId;

    private Long orderId;

    private String attitude;

    private String efficiency;

    private String meter;

    private Date createTime;

    private String evaluation;

}