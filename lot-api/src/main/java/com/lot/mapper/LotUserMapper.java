package com.lot.mapper;

import com.lot.entity.LotUserEntity;
import com.lot.vo.lotUser.LotUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("LotUserMapper")
public interface LotUserMapper {
    /**
     * 根据账号名获取User对象
     * */
    LotUser getUser(String username);
    /**
     * 获取User的List
     * */
    List<LotUser> findList(Map<String,Object> map);

    /**
     * 获取User的List的总数
     * */
    int getCount(Map<String,Object> map);

    /**
     * 判断是否已经有openId
     * */
    int getCountByOpenId(String openId);

    /**
     * 根据openId获取对象
     * */
    LotUserEntity getUserByOpenId(String openId);
}
