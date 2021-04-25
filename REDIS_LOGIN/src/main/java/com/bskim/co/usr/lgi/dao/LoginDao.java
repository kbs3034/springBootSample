package com.bskim.co.usr.lgi.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bskim.co.usr.common.dto.User;

@Mapper
public interface LoginDao {
	User selectUserInfo(User user); 
}
