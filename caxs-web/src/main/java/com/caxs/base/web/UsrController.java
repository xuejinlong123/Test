package com.caxs.base.web;

import com.alibaba.fastjson.JSONObject;
import com.caxs.base.domain.HmUser;
import com.caxs.base.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description:
 * @Author: xuejinlong
 * @system name: 新一代消费金融系统
 * @copyright：长安新生（深圳）金融投资有限公司
 * @Date: Created in  2018/8/2
 */
@Controller
@RequestMapping("/hm_users")
public class UsrController {
    @Autowired
    @Qualifier("user")
    private IUser user;

    /**
     * @description: 用户注册
     * @author xuejl
     * @date 2018/8/2 20:22
     */
    @RequestMapping(value = "new", method = RequestMethod.POST)
    @ResponseBody
    public Object usrRegist(HttpServletRequest request) {
        JSONObject responseJson = new JSONObject();
        responseJson.put("success", false);
        responseJson.put("message", "用户注册失败！");
        try {
            Map param = request.getParameterMap();
            boolean flag = user.usrRegist(param);
            if (flag) {
                responseJson.put("success", true);
                responseJson.put("message", "用户注册成功！");
            } else {
                return responseJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseJson;
    }

    /**
     * @description: 更新用户
     * @author xuejl
     * @date 2018/8/2 20:22
     */
    @RequestMapping(value = "new", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUsrInfo(HttpServletRequest request) {
        JSONObject responseJson = new JSONObject();
        responseJson.put("success", false);
        responseJson.put("message", "用户信息更新失败！");
        try {
            Map param = request.getParameterMap();
            boolean flag = user.updateUsrInfo(param);
            if (flag) {
                responseJson.put("success", true);
                responseJson.put("message", "用户信息更新成功！");
            } else {
                return responseJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseJson;
    }

    /**
     * @description: 查询单个用户信息
     * @author xuejl
     * @date 2018/8/2 20:22
     */
    @RequestMapping(value = "queryOneUsrInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object queryOneUsrInfo(HttpServletRequest request) {
        HmUser hmUser = null;
        try {
            Map param = request.getParameterMap();
            hmUser = user.queryOneUsrInfo(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hmUser;
    }

}
