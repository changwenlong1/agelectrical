package com.example.order.service;

import com.example.order.entity.GSysManager;
import com.example.order.exception.AddUserException;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    /**
     * 公告，新闻，轮播图
     * @param userId type
     * @return
     */
    List<Map<String, Object>> getManagerList(String userId);


    List<Map<String, Object>> getManagerId(String userId);


    void addManger(GSysManager gSysManager)  throws AddUserException;

    void deleteManger(String managerId);

    List<Map<String, Object>> getManger(String managerId);

    void updateManger(GSysManager gSysManager);


}
