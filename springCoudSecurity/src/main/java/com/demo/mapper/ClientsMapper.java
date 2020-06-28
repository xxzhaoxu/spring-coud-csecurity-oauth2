package com.demo.mapper;

import com.demo.entity.Clients;
import org.apache.ibatis.annotations.Param;

/**
 * @author 35086
 */
public interface ClientsMapper {
    int deleteByPrimaryKey(String clientId);

    int insert(Clients record);

    int insertSelective(Clients record);

    Clients selectByPrimaryKey(String clientId);

    int updateByPrimaryKeySelective(Clients record);

    int updateByPrimaryKeyWithBLOBs(Clients record);

    int updateByPrimaryKey(Clients record);

    Clients selectById(String clientId);
}