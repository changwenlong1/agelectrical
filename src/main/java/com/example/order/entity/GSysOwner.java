package com.example.order.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysOwner implements Serializable {

    private Long ownerId;

    private String ownerName;

    private String phone;

    private String address;

    private String managerId;

    private String isDel;

    private String managerName;
}