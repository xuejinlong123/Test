package com.caxs.base.service;

import com.caxs.base.domain.HmUser;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Description:
 * @Author: xuejinlong
 * @system name: 新一代消费金融系统
 * @copyright：长安新生（深圳）金融投资有限公司
 * @Date: Created in  2018/8/2
 */
public interface IUser {
    /**
     * @description: 用户注册
     * @author xuejl
     * @date 2018/8/2 19:28
     */
    public boolean usrRegist(Map<String, Object> map);

    /**
     * @description: 用户登录
     * @author xuejl
     * @date 2018/8/2 19:30
     */
    public Map<String, Object> usrLogin(Map<String, Object> map);

    /**
     * @description: 更新用户
     * @author xuejl
     * @date 2018/8/2 19:31
     */
    public boolean updateUsrInfo(Map<String, Object> map);

    /**
     * @description: 查询单个用户信息
     * @author xuejl
     * @date 2018/8/2 19:33
     */
    public HmUser queryOneUsrInfo(Map<String, Object> map);

    /**
     * @description: 查询用户列表
     * @author xuejl
     * @date 2018/8/2 19:33
     */
    public PageInfo<HmUser> queryUsrList(Map<String, Object> map);
}
