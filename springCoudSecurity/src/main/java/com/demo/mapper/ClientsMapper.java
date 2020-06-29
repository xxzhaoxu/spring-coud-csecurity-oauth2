package com.demo.mapper;

import com.demo.entity.Clients;
import org.apache.ibatis.annotations.Param;

/**
 * @author 35086
 */
public interface ClientsMapper {
    Clients selectById(String clientId);
}