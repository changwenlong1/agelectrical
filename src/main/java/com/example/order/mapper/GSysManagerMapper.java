package com.example.order.mapper;


import com.example.order.entity.GSysManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface GSysManagerMapper {

    List<Map<String, Object>> getManagerList(String userId);

    List<Map<String, Object>> getManagerId(@Param("userId") String userId);

    int addManger(GSysManager gSysManagement);

    int deleteManger(String managerId);

    List<Map<String, Object>> getManger(String managerId);

    void updateManger(GSysManager gSysManager);

    GSysManager managerLogin(String loginName);
}
