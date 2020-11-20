package com.example.order.mapper;

import com.example.order.entity.GSysOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface GSysOrderMapper {

    int insertSelective(GSysOrder record);

    GSysOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(GSysOrder record);

    /**
     * 小程序获取业主上报订单列表
     * @return
     */
    List<Map<String,Object>> getOrderListForOwner(@Param("openId") String openId);

    List<Map<String, Object>> getInfo();

    /**
     * 小程序获取客户经理订单列表
     * @return
     */
    List<Map<String,Object>> getOrderListForManager(@Param("managerId") String managerId);

    /**
     * 小程序获取维修工订单列表
     * @return
     */
    List<Map<String,Object>> getOrderListForWorker(@Param("workerId") String workerId);

    /**
     * 小程序维修工查看订单详情
     * @return
     */
    Map<String,Object> checkOrderDetails(@Param("orderId")Long orderId);
}
