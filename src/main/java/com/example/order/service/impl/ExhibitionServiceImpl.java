package com.example.order.service.impl;

import com.example.order.entity.GSysRich;
import com.example.order.mapper.GSysRichMapper;
import com.example.order.service.ExhibitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {

    protected static final Logger logger = LoggerFactory.getLogger(ExhibitionServiceImpl.class);

    @Autowired
    GSysRichMapper gSysRichMapper;

    @Override
    @Transactional
    public boolean addRich(String content, Integer sort) {
        //新增内容
        GSysRich gSysRich = new GSysRich();
        gSysRich.setContent(content);
        gSysRich.setSort(sort);
        gSysRich.setIsvalid("1");
        gSysRich.setReleaseStatus("0");
        try {
            gSysRichMapper.insertSelective(gSysRich);
        } catch (Exception e) {
            logger.error("新增失败：{}",e.getMessage());
            return false;
        }
        return true;
    }
}
