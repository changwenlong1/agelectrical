package com.example.order.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysOrder implements Serializable {

    private Long orderId;

    private String openId;

    private Long consumerId;

    private String faultType;

    private String problem;

    private String feedbacksrc;

    private String telephone;

    private Long companyId;

    private String address;

    private String contactAddr;

    private String orderStatus;

    private Date createTime;

    private String sendSms;

    private String transferDesc;

}
