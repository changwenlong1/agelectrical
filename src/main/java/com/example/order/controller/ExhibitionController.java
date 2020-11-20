package com.example.order.controller;

import com.example.order.common.Constant;
import com.example.order.service.ExhibitionService;
import com.example.order.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 展示内容Controller
 */
@Controller
@RequestMapping("/api/rich")
public class ExhibitionController {

    protected static final Logger logger = LoggerFactory.getLogger(ExhibitionController.class);

    @Autowired
    ExhibitionService exhibitionService;

    @RequestMapping(value = "/addRich",method = RequestMethod.POST)
    public void addRich(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("content") String content,
                            @RequestParam("sort") Integer sort){

        Map<String, Object> res = new HashMap<String, Object>();
        boolean b = exhibitionService.addRich(content, sort);
        if(b){
            res.put(Constant.RESPONSE_DATA, "新增成功");
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            ServletUtils.writeToResponse(response,res);
        }else {
            res.put(Constant.RESPONSE_DATA, "新增失败");
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            ServletUtils.writeToResponse(response,res);
        }

    }
}
