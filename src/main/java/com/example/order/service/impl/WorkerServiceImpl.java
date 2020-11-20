package com.example.order.service.impl;


import com.example.order.entity.GSysWorker;
import com.example.order.exception.AddUserException;
import com.example.order.mapper.GSysWorkerMapper;
import com.example.order.service.WorkerService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    protected static final Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @Autowired
    private GSysWorkerMapper gSysWorkerMapper;


    @Override
    public List<GSysWorker> getWorkers(String grade) {
        return gSysWorkerMapper.getWorkers(grade);
    }

    @Override
    public void addWorker(GSysWorker gSysWorker) throws AddUserException {
        try {
            gSysWorkerMapper.addWorker(gSysWorker);
        } catch (Exception e) {
            logger.error("新增失败!");
            throw new AddUserException("新增失败");
        }
    }

    @Override
    public void deleteWorker(String workerId) {
        try {
            gSysWorkerMapper.deleteWorker(workerId);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }

    @Override
    public GSysWorker getWorker(@Param("workerId")String workerId) {
        return gSysWorkerMapper.getWorker(workerId);

    }

    @Override
    public void updateWorker(GSysWorker gSysWorker) throws AddUserException {
        try {
            gSysWorkerMapper.updateWorker(gSysWorker);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }

}
