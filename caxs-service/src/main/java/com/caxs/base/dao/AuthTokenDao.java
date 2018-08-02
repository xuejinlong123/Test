package com.caxs.base.dao;

import com.caxs.base.domain.AuthToken;

public interface AuthTokenDao {
    int deleteByPrimaryKey(String id);

    int insert(AuthToken record);

    int insertSelective(AuthToken record);

    AuthToken selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AuthToken record);

    int updateByPrimaryKey(AuthToken record);
}