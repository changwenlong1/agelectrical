package com.example.order.service;

import com.example.order.entity.GSysWorker;
import com.example.order.exception.AddUserException;

import java.util.List;

public interface WorkerService {

    /**
     * 用户管理
     * @param type type
     * @return
     */
    List<GSysWorker> getWorkers(String grade);

    void deleteWorker(String workerId);

    void addWorker(GSysWorker gSysWorker)  throws AddUserException;

    GSysWorker getWorker(String workerId);

    void updateWorker(GSysWorker gSysWorker);
}
