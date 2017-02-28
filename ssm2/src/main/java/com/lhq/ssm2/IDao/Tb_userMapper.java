package com.lhq.ssm2.IDao;

import com.lhq.ssm2.domain.Tb_user;

public interface Tb_userMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tb_user record);

    int insertSelective(Tb_user record);

    Tb_user selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tb_user record);

    int updateByPrimaryKey(Tb_user record);
}