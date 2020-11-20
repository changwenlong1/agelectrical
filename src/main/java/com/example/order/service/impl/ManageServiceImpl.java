package com.example.order.service.impl;

import com.example.order.entity.*;
import com.example.order.exception.AddUserException;
import com.example.order.mapper.*;
import com.example.order.service.ManageService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {

    protected static final Logger logger = LoggerFactory.getLogger(ManageServiceImpl.class);

    @Autowired
    private GSysManageMapper  gSysManageMapper;

    @Autowired
    private GSysOrderMapper gSysOrderMapper;

    @Autowired
    private GSysEvaluateMapper gSysEvaluateMapper;

    @Autowired
    private GSysOrderProgressMapper gSysOrderProgressMapper;

    @Autowired
    private GSysWorkerMapper gSysWorkerMapper;

    @Override
    public GSysManage getInfo(@Param("objId")Long objId,@Param("type")String type) {
        GSysManage gSysManage = null;
        try {
            gSysManage = gSysManageMapper.getInfo(objId, type);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return gSysManage;
    }

    @Override
    public List<GSysManage> getList(@Param("type")String type,@Param("searchContent")String searchContent) {
        return gSysManageMapper.getList(type,searchContent);

    }

    @Override
    public List<Map<String, Object>> getNewsList(@Param("type")String type,@Param("searchContent")String searchContent) {
        return gSysManageMapper.getNewsList(type,searchContent);

    }

    @Override
    public void addInfo(GSysManage gSysManage) throws AddUserException {
        try {
            gSysManageMapper.insertSelective(gSysManage);
        } catch (Exception e) {
            logger.error("新增失败!");
            throw new AddUserException("新增失败");
        }
    }

    @Override
    public void updateList(GSysManage gSysManage) {
        try {
            gSysManageMapper.updateByPrimaryKeySelective(gSysManage);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }


    @Override
    public void doDelete(String objId) {
        try {
            gSysManageMapper.updateIsdelByOId(objId);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }

    @Override
    public List<GSysManage> getManageList(String type, String tableId,String searchContent) {
        return gSysManageMapper.getManageList(type,tableId,searchContent);
    }

    @Override
    public GSysManage getManageInfo(Long objId, String type) {
        GSysManage gSysManage = null;
        try {
            gSysManage = gSysManageMapper.getManageByTypeAndObjId(objId, type);
            Long readNum = gSysManage.getReadnum();
            gSysManage.setReadnum(readNum + 1);
            gSysManageMapper.updateByPrimaryKeySelective(gSysManage);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return gSysManage;
    }

    @Override
    public boolean reviewById(Long objId,String exaId,String remark) {
        try {
            GSysManage gSysManage = gSysManageMapper.selectByPrimaryKey(objId);

            gSysManage.setExamineStatus(exaId);
            gSysManage.setRemark(remark);
            gSysManageMapper.updateByPrimaryKeySelective(gSysManage);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    public boolean releaseById(Long objId) {
        try {
            GSysManage gSysManage = gSysManageMapper.selectByPrimaryKey(objId);
            Date date = new Date();
            gSysManage.setReleasetime(date);
            gSysManage.setReleaseStatus("1");
            gSysManageMapper.updateByPrimaryKeySelective(gSysManage);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getTableList() {
        return gSysManageMapper.getTableList("news");
    }

    @Override
    public List<Map<String, Object>> getFailTypeList() {
        return gSysManageMapper.getTableList("failType");
    }

    @Override
    public List<Map<String, Object>> getUnitList() {
        return gSysManageMapper.getUnitList();
    }

    @Override
    public void addOrder(GSysOrder gSysOrder) {
        try {
            //上报维修订单,生成订单表数据
            gSysOrderMapper.insertSelective(gSysOrder);
            //生成订单进度表记录
            GSysOrderProgress gSysOrderProgress = new GSysOrderProgress();
            gSysOrderProgress.setOrderId(gSysOrder.getOrderId());
            gSysOrderProgress.setStatus("01");
            gSysOrderProgress.setCreatTime(new Date());
            gSysOrderProgressMapper.insertSelective(gSysOrderProgress);
            //todo 判断是否开启上报订单后自动发短信功能

        } catch (Exception e) {
            logger.error("故障上报失败{}",e.getMessage());
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("故障上报失败");
        }
    }

    @Override
    public List<Map<String,Object>> getOrderListForOwner(String openId) {
        return gSysOrderMapper.getOrderListForOwner(openId);
    }

    @Override
    public void orderEvaluate(GSysEvaluate gSysEvaluate) {
        try {
            //查询该笔订单信息
            GSysOrder gSysOrder = gSysOrderMapper.selectByPrimaryKey(gSysEvaluate.getOrderId());
            //评价订单
            gSysEvaluateMapper.insertSelective(gSysEvaluate);
            //评价订单后把该订单状态置为已完成
            gSysOrder.setOrderStatus("04");
            gSysOrderMapper.updateByPrimaryKeySelective(gSysOrder);
            //生成订单进度表记录
            GSysOrderProgress gSysOrderProgress = new GSysOrderProgress();
            gSysOrderProgress.setOrderId(gSysOrder.getOrderId());
            gSysOrderProgress.setStatus("04");
            gSysOrderProgress.setCreatTime(new Date());
            gSysOrderProgressMapper.insertSelective(gSysOrderProgress);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("订单评价失败");
        }
    }

    @Override
    public void reportAgain(Long orderId) {
        try {
            //查询该笔订单信息
            GSysOrder gSysOrder = gSysOrderMapper.selectByPrimaryKey(orderId);
            //把该笔订单状态置为待维修
            gSysOrder.setOrderStatus("02");
            gSysOrderMapper.updateByPrimaryKeySelective(gSysOrder);
            //生成订单进度表记录
            GSysOrderProgress gSysOrderProgress = new GSysOrderProgress();
            gSysOrderProgress.setOrderId(orderId);
            gSysOrderProgress.setStatus("02");
            gSysOrderProgress.setCreatTime(new Date());
            gSysOrderProgressMapper.insertSelective(gSysOrderProgress);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("订单重新上报失败");
        }
    }

    @Override
    public List<Map<String, Object>> getOrderProgress(Long orderId) {
        return gSysOrderProgressMapper.getOrderProgress(orderId);
    }

    @Override
    public List<Map<String, Object>> getOrderListForManager(String managerId) {
        return gSysOrderMapper.getOrderListForManager(managerId);
    }

    @Override
    public GSysEvaluate checkEvaluationDetail(Long orderId) {
        return gSysEvaluateMapper.checkEvaluationDetail(orderId);
    }

    @Override
    public List<Map<String, Object>> getOrderListForWorker(String workerId) {
        return gSysOrderMapper.getOrderListForWorker(workerId);
    }

    @Override
    public List<Map<String, Object>> getWorkerArray(String workerId) {
        List<Map<String, Object>> workerArray = null;
        try {
            //根据workerId查询该维修工的基本信息,判断该维修工的等级
            GSysWorker worker = gSysWorkerMapper.getWorker(workerId);
            //维修工等级 0:一级维修工 1:二级维修工 2:三级维修工
            //注:只有一级维修工和二级维修工可以转派订单
            String grade = worker.getGrade();
            if (StringUtils.equals(grade,"0")){
                //当前维修工等级为一级,则获取二级维修工列表
                workerArray = gSysWorkerMapper.getWorkerArray("1");
            }else if (StringUtils.equals(grade,"1")){
                //当前维修工等级为二级,则获取三级维修工列表
                workerArray = gSysWorkerMapper.getWorkerArray("2");
            }else{
                workerArray = null;
            }
        } catch (Exception e) {
            logger.error("查询可转派维修工列表失败:{}",e.getMessage());
            workerArray = null;
        }

        return workerArray;
    }

    @Override
    public void transferOrder(Long orderId, Long transferUserId, String transferDesc) {
        //根据订单ID查询要转派的订单,只有订单状态为02:待维修 才可以转派
        GSysOrder gSysOrder = gSysOrderMapper.selectByPrimaryKey(orderId);
        //判断订单状态是否为02:待维修,如果不是则抛异常
        if (!StringUtils.equals(gSysOrder.getOrderStatus(),"02")){
            throw new RuntimeException("该订单不可转派!");
        }
        try {
            //转派订单
            gSysOrder.setConsumerId(transferUserId);
            gSysOrder.setTransferDesc(transferDesc);
            gSysOrderMapper.updateByPrimaryKeySelective(gSysOrder);
        } catch (Exception e) {
            throw new RuntimeException("转派失败!");
        }
    }

    @Override
    public void completeOrder(Long orderId) {
        try {
            //根据订单ID查询订单
            GSysOrder gSysOrder = gSysOrderMapper.selectByPrimaryKey(orderId);
            gSysOrder.setOrderStatus("03");
            gSysOrderMapper.updateByPrimaryKeySelective(gSysOrder);
            //生成订单进度表记录
            GSysOrderProgress gSysOrderProgress = new GSysOrderProgress();
            gSysOrderProgress.setOrderId(orderId);
            gSysOrderProgress.setStatus("03");
            gSysOrderProgress.setCreatTime(new Date());
            gSysOrderProgressMapper.insertSelective(gSysOrderProgress);
        } catch (Exception e) {
            logger.error("维修工完成订单失败:{}",e.getMessage());
            throw new RuntimeException("操作失败!");
        }
    }

    @Override
    public Map<String, Object> checkOrderDetails(Long orderId) {
        Map<String, Object> orderDetails = gSysOrderMapper.checkOrderDetails(orderId);
        Object photos = orderDetails.get("photos");
        String src = "";
        if (isObjectNotEmpty(photos)){
            String photo = String.valueOf(orderDetails.get("photos"));
            String[] split = photo.replace("\"[", "").replace("]\"", "").split(",");
            src = split[0];
        }
        orderDetails.put("src",src);
        return orderDetails;
    }

    /**
     * 判断Object对象为空或空字符串
     * @param obj
     * @return
     */
    public Boolean isObjectNotEmpty(Object obj) {
        String str = ObjectUtils.toString(obj, "");
        return StringUtils.isNotBlank(str);
    }

    public static void main(String[] args) {

        String str = "[\"http://localhost:8081/2020-09-25/1601029804908_378.jpg\",\"http://localhost:8081/2020-09-25/1601029804908_378.jpg\"]";

        String[] split = str.replace("[", "").replace("]", "").split(",");

        System.out.println(split[0].replace("", "").replace("]", ""));
    }

}
