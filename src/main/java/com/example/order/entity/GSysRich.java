package com.example.order.entity;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysRich implements Serializable {

    private Long richId;

    private String isvalid;

    private Integer sort;

    private String releaseStatus;


    private String content;

}