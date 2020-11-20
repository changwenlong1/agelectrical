package com.example.order.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysMenu implements Serializable {

    private Long menuId;

    private String menuName;

    private String remark;

    private Integer parentId;

    private String isValid;

    private Long sort;

}
