package com.example.order.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysCompany implements Serializable {

    private Long companyId;

    private String userName;

    private String userTel;

    private String companyName;

    private String address;

    private String industry;

    private String creattime;

    private String isValid;

    private String isDel;
}