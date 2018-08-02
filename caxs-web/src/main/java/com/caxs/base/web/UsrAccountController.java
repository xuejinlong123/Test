package com.caxs.base.web;

import com.alibaba.fastjson.JSONObject;
import com.caxs.base.service.IUser;
import com.caxs.base.utils.JsonUtils;
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
@RequestMapping("/account")
public class UsrAccountController {
    @Autowired
    @Qualifier("user")
    private IUser user;

    /**
     * @description: 用户登录
     * @author xuejl
     * @date 2018/8/2 20:22
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Object usrRegist(HttpServletRequest request) {
        JSONObject responseJson = new JSONObject();
        try {
            Map param = request.getParameterMap();
            Map<String, Object> resultMap = user.usrLogin(param);
            JsonUtils.mapToJson(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseJson;
    }

}
