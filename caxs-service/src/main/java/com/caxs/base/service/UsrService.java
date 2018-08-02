package com.caxs.base.service;

import com.caxs.base.dao.AuthTokenDao;
import com.caxs.base.dao.HmUserDao;
import com.caxs.base.domain.HmUser;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: xuejinlong
 * @system name: 新一代消费金融系统
 * @copyright：长安新生（深圳）金融投资有限公司
 * @Date: Created in  2018/8/2
 */
@Component
public class UsrService implements IUser {
    private static final Logger logger = LoggerFactory.getLogger(UsrService.class);
    @Autowired
    private HmUserDao hmUserDao;
    @Autowired
    private AuthTokenDao authTokenDao;
    @Autowired
    private UtilsService utilsService;

    @Override
    public boolean usrRegist(Map<String, Object> map) {
        HmUser hmUser;
        boolean flag = false;
        try {
            hmUser = (HmUser) utilsService.convertMap(HmUser.class, map);
            if (hmUser != null) {
                // 对用户密码进行加密
                hmUser.setPassword(utilsService.MD5(hmUser.getAvatar()));
            }
            hmUserDao.insertSelective(hmUser);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("map转换成domain时出现异常！错误信息：" + e.getMessage());
        }
        return flag;
    }

    @Override
    public Map<String, Object> usrLogin(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>(4);
        // 用户名
        String loginId = (String) map.get("loginId");
        // 密码
        String password = (String) map.get("password");
        Map<String, Object> param = new HashMap<>();
        param.put("loginid", loginId);
        // 对密码进行加密与数据库对比
        HmUser hmUser = hmUserDao.selectOneUsrInfo(param);
        if (hmUser == null) {
            resultMap.put("message", "查询无此用户！");
            resultMap.put("success", false);
        } else {
            password = utilsService.MD5(password);
            if (hmUser.getPassword().equals(password)) {
                resultMap.put("message", "用户登陆成功！");
                resultMap.put("success", true);
            } else {
                resultMap.put("message", "用户密码不正确！");
                resultMap.put("success", false);
            }
        }
        return resultMap;
    }

    @Override
    public boolean updateUsrInfo(Map<String, Object> map) {
        HmUser hmUser;
        boolean flag = false;
        try {
            hmUser = (HmUser) utilsService.convertMap(HmUser.class, map);
            hmUserDao.updateByLoginIdSelective(hmUser);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("map转换成domain时出现异常！错误信息：" + e.getMessage());
        }
        return flag;
    }

    @Override
    public HmUser queryOneUsrInfo(Map<String, Object> map) {
        HmUser hmUser = hmUserDao.selectOneUsrInfo(map);
        return hmUser;
    }

    @Override
    public PageInfo<HmUser> queryUsrList(Map<String, Object> map) {
        List<HmUser> list = hmUserDao.queryUsrList(map);
        return new PageInfo<>(list);
    }
}
