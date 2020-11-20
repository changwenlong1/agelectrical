package com.example.order.mapper;

import com.example.order.entity.GSysEvaluate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface GSysEvaluateMapper {

    int insertSelective(GSysEvaluate record);

    GSysEvaluate selectByPrimaryKey(Long evaluateId);

    int updateByPrimaryKeySelective(GSysEvaluate record);

    /**
     * 小程序客户经理查看评价
     * @return
     */
    GSysEvaluate checkEvaluationDetail(@Param("orderId")Long orderId);
}
