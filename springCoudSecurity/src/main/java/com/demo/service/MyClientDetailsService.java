package com.demo.service;

import com.demo.entity.Clients;
import com.demo.mapper.ClientsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyClientDetailsService implements ClientDetailsService {
    @Resource
    private ClientsMapper clientsMapper;
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        System.out.println("clientId: "+ s);
        if (s==null||s.trim().length()<=0) {
            throw new ClientRegistrationException(" clientId 为空");
        }

        Clients clients = clientsMapper.selectById(s);
        if (clients != null){
            return clients;
        }
        throw new ClientRegistrationException("client 客户端 不存在!");
    }


}
