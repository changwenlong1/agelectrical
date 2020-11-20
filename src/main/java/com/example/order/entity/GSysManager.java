package com.example.order.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysManager implements Serializable {

    private Long managerId;

    private String managerName;

    private String gender;

    private String phone;

    private String password;

    private String companyId;

    private String isDel;

    private String isValid;

    //管理员id
    private Long userId;
}
