package com.example.order.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysUserMenu implements Serializable {

    private Long userId;

    private Long menuId;

}
