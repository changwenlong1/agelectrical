package com.example.order.service.impl;

import com.example.order.mapper.GSysOrderMapper;
import com.example.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OrderServiceImpl implements OrderService {

    protected static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private GSysOrderMapper gSysOrderMapper;

    @Override
    public List<Map<String, Object>> getList() {
        return gSysOrderMapper.getInfo();

    }

}
