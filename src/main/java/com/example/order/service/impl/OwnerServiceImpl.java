package com.example.order.service.impl;


import com.example.order.entity.GSysOwner;
import com.example.order.exception.AddUserException;
import com.example.order.mapper.GSysOwnerMapper;
import com.example.order.service.OwnerService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    protected static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Autowired
    private GSysOwnerMapper gSysOwnerMapper;


    @Override
    public List<GSysOwner> getOwnerList( String userId) {
        return gSysOwnerMapper.getOwnerList(userId);
    }

    @Override
    public void addOwner(GSysOwner gSysOwner) throws AddUserException {
        try {
            gSysOwnerMapper.addOwner(gSysOwner);
        } catch (Exception e) {
            logger.error("新增失败!");
            throw new AddUserException("新增失败");
        }
    }


    @Override
    public void deleteOwner(String ownerId) {
        try {
            gSysOwnerMapper.deleteOwner(ownerId);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }


    @Override
    public GSysOwner getOwner(@Param("ownerId")String ownerId) {
        return gSysOwnerMapper.getOwner(ownerId);

    }

    @Override
    public void updateOwner(GSysOwner gSysOwner) throws AddUserException {
        try {
            gSysOwnerMapper.updateOwner(gSysOwner);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }
}
