package com.example.order.service.impl;


import com.example.order.entity.GSysManager;
import com.example.order.exception.AddUserException;
import com.example.order.mapper.GSysCompanyMapper;
import com.example.order.mapper.GSysManagerMapper;
import com.example.order.service.ManagerService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    protected static final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private GSysManagerMapper gSysManagerMapper;

    @Autowired
    private GSysCompanyMapper gSysCompanyMapper;

    @Override
    public List<Map<String, Object>> getManagerList(String userId) {
        return gSysManagerMapper.getManagerList(userId);

    }


    @Override
    public List<Map<String, Object>> getManagerId(String userId) {
        return gSysManagerMapper.getManagerId(userId);
    }
    @Override
    public void addManger(GSysManager gSysManager) throws AddUserException {
        try {
            Long userId = gSysManager.getUserId();
            Long companyId = gSysCompanyMapper.selectCompanyIdForUserId(userId);
            gSysManager.setCompanyId(String.valueOf(companyId));
            gSysManagerMapper.addManger(gSysManager);
        } catch (Exception e) {
            logger.error("新增失败!");
            throw new AddUserException("新增失败");
        }
    }
    @Override
    public void deleteManger(String managerId) {
        try {
            gSysManagerMapper.deleteManger(managerId);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }

    @Override
    public  List<Map<String, Object>> getManger(@Param("managerId")String managerId) {
        return gSysManagerMapper.getManger(managerId);

    }
    @Override
    public void updateManger(GSysManager gSysManager) throws AddUserException {
        try {
            gSysManagerMapper.updateManger(gSysManager);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }


}
