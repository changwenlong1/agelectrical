package com.example.order.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddUserRequestParam {

    private String username;
    private String wechatOpenId;
    private String menus;

}
