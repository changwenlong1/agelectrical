package com.example.order.service;

import com.example.order.entity.GSysUser;
import com.example.order.exception.AddUserException;
import com.example.order.request.AddUserRequestParam;

import java.util.Map;

public interface UserService {

    /**
     * 获取所有菜单
     * @return list
     */
    Map<String,Object> getMenuList(Long userId);

    GSysUser getMenuListByLoginName(String loginName);

    void addUser(AddUserRequestParam addUserRequestParam) throws AddUserException;

    long addCompanyUser(GSysUser gSysUser) throws AddUserException;

    void updateCompanyUserStatus(GSysUser gSysUser);

}
