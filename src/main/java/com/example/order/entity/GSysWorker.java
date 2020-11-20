package com.example.order.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysWorker implements Serializable {

    private Long workerId;

    private String workerName;

    private String gender;

    private String phone;

    private String password;

    private String isValid;

    private String grade;

    private String isDel;
}
