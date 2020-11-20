package com.example.order.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GSysManage implements Serializable {

    private Long objId;

    private Long userId;

    private String title;

    private String type;

    private String content;

    private String url;

    private String creattime;

    private Date releasetime;

    private Long readnum;

    private Long sort;

    private String releaseStatus;

    private String examineStatus;

    private String isValid;

    private String isDel;

    private String remark;

    private String userName;

    private String selectId;

}