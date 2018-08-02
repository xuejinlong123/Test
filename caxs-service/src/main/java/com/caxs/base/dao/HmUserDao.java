package com.caxs.base.dao;

import com.caxs.base.domain.HmUser;

import java.util.List;
import java.util.Map;

public interface HmUserDao {
    int deleteByPrimaryKey(String id);

    int insert(HmUser record);

    int insertSelective(HmUser record);

    HmUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HmUser record);

    int updateByPrimaryKey(HmUser record);

    HmUser selectOneUsrInfo(Map<String, Object> param);

    int updateByLoginIdSelective(HmUser record);

    List<HmUser> queryUsrList(Map<String, Object> map);
}