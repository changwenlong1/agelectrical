package com.example.order.service;

import com.example.order.entity.GSysOwner;
import com.example.order.exception.AddUserException;

import java.util.List;

public interface OwnerService {

    /**
     * 用户管理
     * @param userId userId
     * @return
     */
    List<GSysOwner> getOwnerList(String userId);

    void addOwner(GSysOwner gSysOwner)  throws AddUserException;

    void deleteOwner(String ownerId);

    GSysOwner getOwner(String ownerId);

    void updateOwner(GSysOwner gSysOwner);

}
