package com.example.order.entity;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="对象")
public class GSysManage implements Serializable {
    @ApiModelProperty(value = "id",position = 1)
    private Long objId;

    @ApiModelProperty(value = "",position = 2)
    private Long userId;

    @ApiModelProperty(value = "",position = 3)
    private String title;

    @ApiModelProperty(value = "",position = 4)
    private String type;

    @ApiModelProperty(value = "",position = 5)
    private String content;

    @ApiModelProperty(value = "",position = 6)
    private String url;

    @ApiModelProperty(value = "",position = 7)
    private String creattime;

    @ApiModelProperty(value = "",position = 8)
    private Date releasetime;

    @ApiModelProperty(value = "",position = 9)
    private Long readnum;

    @ApiModelProperty(value = "",position = 10)
    private Long sort;

    @ApiModelProperty(value = "",position = 11)
    private String releaseStatus;

    @ApiModelProperty(value = "",position = 12)
    private String examineStatus;

    @ApiModelProperty(value = "",position = 13)
    private String isValid;

    @ApiModelProperty(value = "",position = 14)
    private String isDel;

    @ApiModelProperty(value = "",position = 15)
    private String remark;

    @ApiModelProperty(value = "",position = 16)
    private String userName;

    @ApiModelProperty(value = "",position = 17)
    private String selectId;

}