package com.example.order.mapper;

import com.example.order.entity.GSysOrderProgress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface GSysOrderProgressMapper {

    int insertSelective(GSysOrderProgress record);

    GSysOrderProgress selectByPrimaryKey(Long progressId);

    int updateByPrimaryKeySelective(GSysOrderProgress record);

    /**
     * 小程序获取订单进度
     * @return
     */
    List<Map<String,Object>> getOrderProgress(@Param("orderId") Long orderId);

}
