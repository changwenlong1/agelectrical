package com.example.order.mapper;


import com.example.order.entity.GSysOwner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GSysOwnerMapper {


    List<GSysOwner> getOwnerList(@Param("userId") String userId);

    int addOwner(GSysOwner gSysOwner);

    void deleteOwner(String ownerId);

    GSysOwner getOwner(String ownerId);

    void updateOwner(GSysOwner gSysOwner);

}