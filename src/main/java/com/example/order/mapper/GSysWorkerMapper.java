package com.example.order.mapper;


import com.example.order.entity.GSysWorker;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface GSysWorkerMapper {

    GSysWorker selectByPrimaryKey(Long workerId);

    GSysWorker workerLogin(String loginName);

    List<GSysWorker> getWorkers(String grade);

    void deleteWorker(String workerId);

    int addWorker(GSysWorker gSysWorker);

    GSysWorker getWorker(String workerId);

    void updateWorker(GSysWorker gSysWorker);

    /**
     * 小程序维修工转派订单获取可转派维修工列表
     * @return
     */
    List<Map<String,Object>> getWorkerArray(@Param("grade") String grade);

}
