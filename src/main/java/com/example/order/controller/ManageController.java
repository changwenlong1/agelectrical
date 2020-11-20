package com.example.order.controller;

import com.example.order.common.Constant;
import com.example.order.entity.GSysEvaluate;
import com.example.order.entity.GSysOrder;
import com.example.order.entity.OpenIdJson;
import com.example.order.request.VChartParam;
import com.example.order.service.ManageService;
import com.example.order.utils.HttpUtil;
import com.example.order.utils.ServletUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.order.entity.GSysManage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ManageController
 * @Description 小程序端Controller
 * @Author xionggy
 * @Date 2020/9/10
 * @Version 1.0
 */
@Controller
@RequestMapping("/api/vchart/manage")
public class ManageController {

    protected static final Logger logger = LoggerFactory.getLogger(ManageController.class);

    @Autowired
    ManageService manageService;

    @Value("${sysPara.appId}")
    private String appId;

    @Value("${sysPara.appSecret}")
    private String appSecret;

    /**
     * 小程序获取openId
     */
    @PostMapping("/getOpenId")
    public void userLogin(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody VChartParam vChartParam) throws IOException {
        Map<String, Object> res = new HashMap<String, Object>();
        String result = "";
        String code = vChartParam.getCode();
        try{//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            result = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + this.appId + "&secret="
                            + this.appSecret + "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result,OpenIdJson.class);
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_DATA, openIdJson.getOpenid());
        ServletUtils.writeToResponse(response, res);
    }

    /**
     * 小程序首页轮播图，公告，新闻列表
     * @param type
     * @param searchContent
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public void getInfo(@RequestParam("type") String type,
                        @RequestParam(value = "tableId",required = false) String tableId,
                        @RequestParam(value="searchContent",required=false)String searchContent,
                        HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<GSysManage> gSysManageList= manageService.getManageList(type,tableId,searchContent);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysManageList);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序首页轮播图，公告，新闻详细信息
     * @param objId
     * @param type
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    public void getList(@RequestParam("objId") Long objId,
                            @RequestParam("type") String type,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            GSysManage gSysManage= manageService.getManageInfo(objId,type);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysManage);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序首页获取新闻分区
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getTableList",method = RequestMethod.GET)
    public void getTableList(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> tableList = manageService.getTableList();
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, tableList);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序故障上报获取故障类型列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getFailTypeList",method = RequestMethod.GET)
    public void getFailTypeList(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> failTypeList = manageService.getFailTypeList();
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, failTypeList);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序故障上报获取业主单位列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getUnitList",method = RequestMethod.GET)
    public void getUnitList(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> unitList = manageService.getUnitList();
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, unitList);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序故障上报
     * @param request
     * @param response
     * @param openId OPENID
     * @param faultType 故障类型
     * @param problem 遇到的问题
     * @param photoList 反馈图片
     * @param phone 联系电话
     * @param unit 单位ID
     * @param contactaddress 联系地址
     * @param address 详细地址
     */
    @RequestMapping(value = "/faultReport",method = RequestMethod.GET)
    public void faultReport(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("openId") String openId,
                            @RequestParam("faultType") String faultType,
                            @RequestParam("problem") String problem,
                            @RequestParam("photoList") String photoList,
                            @RequestParam("phone") String phone,
                            @RequestParam("unit") Long unit,
                            @RequestParam("contactaddress") String contactaddress,
                            @RequestParam("address") String address){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            if (StringUtils.equals(photoList,"[]")){
                photoList = "";
            }
            GSysOrder gSysOrder = new GSysOrder();
            gSysOrder.setOpenId(openId);
            gSysOrder.setFaultType(faultType);
            gSysOrder.setProblem(problem);
            gSysOrder.setFeedbacksrc(photoList);
            gSysOrder.setTelephone(phone);
            gSysOrder.setCompanyId(unit);
            gSysOrder.setAddress(contactaddress);
            gSysOrder.setContactAddr(address);
            gSysOrder.setCreateTime(new Date());
            gSysOrder.setOrderStatus("01");
            gSysOrder.setOrderStatus("01");
            manageService.addOrder(gSysOrder);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, "报修成功");
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序获取业主上报订单列表
     * @param request
     * @param response
     * @param openId OPENID
     */
    @RequestMapping(value = "/getOrderListForOwner",method = RequestMethod.GET)
    public void getOrderListForOwner(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("openId") String openId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> orderListForOwner = manageService.getOrderListForOwner(openId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, orderListForOwner);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序业主订单评价
     * @param request
     * @param response
     * @param orderId 订单id
     * @param attitude 服务态度评分
     * @param efficiency 工作效率评分
     * @param meter 仪表整洁评分
     * @param evaluation 评价描述
     */
    @RequestMapping(value = "/orderEvaluate",method = RequestMethod.GET)
    public void orderEvaluate(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam("orderId") Long orderId,
                                      @RequestParam("attitude") String attitude,
                                      @RequestParam("efficiency") String efficiency,
                                      @RequestParam("meter") String meter,
                                      @RequestParam("evaluation") String evaluation){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            GSysEvaluate gSysEvaluate = new GSysEvaluate();
            gSysEvaluate.setOrderId(orderId);
            gSysEvaluate.setAttitude(attitude);
            gSysEvaluate.setEfficiency(efficiency);
            gSysEvaluate.setMeter(meter);
            gSysEvaluate.setEvaluation(evaluation);
            gSysEvaluate.setCreateTime(new Date());

            manageService.orderEvaluate(gSysEvaluate);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, "评价成功");
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序订单重新上报
     * @param request
     * @param response
     * @param orderId 订单id
     */
    @RequestMapping(value = "/reportAgain",method = RequestMethod.GET)
    public void reportAgain(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam("orderId") Long orderId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            manageService.reportAgain(orderId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, "重新上报成功");
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序获取订单进度
     * @param request
     * @param response
     * @param orderId 订单id
     */
    @RequestMapping(value = "/getOrderProgress",method = RequestMethod.GET)
    public void getOrderProgress(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("orderId") Long orderId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> orderProgress = manageService.getOrderProgress(orderId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, orderProgress);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序获取客户经理订单列表
     * @param request
     * @param response
     * @param managerId 客户经理Id
     */
    @RequestMapping(value = "/getOrderListForManager",method = RequestMethod.GET)
    public void getOrderListForManager(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam("managerId") String managerId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> orderList = manageService.getOrderListForManager(managerId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, orderList);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序客户经理查看评价
     * @param request
     * @param response
     * @param orderId 订单id
     */
    @RequestMapping(value = "/checkEvaluationDetail",method = RequestMethod.GET)
    public void checkEvaluationDetail(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("orderId") Long orderId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            GSysEvaluate gSysEvaluate = manageService.checkEvaluationDetail(orderId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysEvaluate);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序获取维修工订单列表
     * @param request
     * @param response
     * @param workerId 维修工Id
     */
    @RequestMapping(value = "/getOrderListForWorker",method = RequestMethod.GET)
    public void getOrderListForWorker(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("workerId") String workerId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> orderList = manageService.getOrderListForWorker(workerId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, orderList);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序维修工转派订单获取可转派维修工列表
     * @param request
     * @param response
     * @param workerId 维修工Id
     */
    @RequestMapping(value = "/getWorkerArray",method = RequestMethod.GET)
    public void getWorkerArray(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam("workerId") String workerId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> workerList = manageService.getWorkerArray(workerId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, workerList);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序维修工转派订单
     * @param request
     * @param response
     * @param orderId 订单ID
     * @param transferUserId 转派维修工ID
     * @param transferDesc 转派描述
     */
    @RequestMapping(value = "/transferOrder",method = RequestMethod.GET)
    public void getWorkerArray(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("orderId") Long orderId,
                               @RequestParam("transferUserId") Long transferUserId,
                               @RequestParam("transferDesc") String transferDesc){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            manageService.transferOrder(orderId,transferUserId,transferDesc);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, "转派成功");
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序维修工完成订单
     * @param request
     * @param response
     * @param orderId 订单ID
     */
    @RequestMapping(value = "/completeOrder",method = RequestMethod.GET)
    public void completeOrder(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("orderId") Long orderId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            manageService.completeOrder(orderId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, "操作成功");
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }

    /**
     * 小程序维修工查看订单详情
     * @param request
     * @param response
     * @param orderId 订单ID
     */
    @RequestMapping(value = "/checkOrderDetails",method = RequestMethod.GET)
    public void checkOrderDetails(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("orderId") Long orderId){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            Map<String, Object> orderDetails = manageService.checkOrderDetails(orderId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, orderDetails);
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }
    }
}
