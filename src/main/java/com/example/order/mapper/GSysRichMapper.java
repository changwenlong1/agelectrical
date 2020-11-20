package com.example.order.mapper;

import com.example.order.entity.GSysRich;
import org.springframework.stereotype.Component;

@Component
public interface GSysRichMapper {

    int insertSelective(GSysRich record);

    GSysRich selectByPrimaryKey(Long richId);

    int updateByPrimaryKeySelective(GSysRich record);

}