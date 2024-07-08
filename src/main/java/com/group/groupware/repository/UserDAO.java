package com.group.groupware.repository;

import com.group.groupware.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public class UserDAO {
    @Autowired
    SqlSession session;

    public List<User> LoginChk (User params){
        return session.selectList("UserDAO.isExist", params);
    }
}
