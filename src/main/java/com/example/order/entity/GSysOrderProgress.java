package com.example.order.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysOrderProgress implements Serializable {

    private Long progressId;

    private Long orderId;

    private Date creatTime;

    private String status;

}