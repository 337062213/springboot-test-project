package com.springboot.test.service.impl;

import com.springboot.test.model.Page;
import com.springboot.test.model.po.User;
import com.springboot.test.model.vo.UserGroupVo;
import com.springboot.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private com.springboot.test.mapper.one.UserMapper userMapper1;
    
    @Autowired
    private com.springboot.test.mapper.two.UserMapper2 userMapper2;

    @Override
    public User insertUser(User user) {
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        Date date = new Date();
        user.setCreatTime(date);
        user.setUpdateTime(date);
        userMapper1.insertUser(user);
        return userMapper1.findUserById(user.getId());
    }

    @Override
    public Integer deleteUser(String uId) {
        return userMapper1.deleteUser(uId);
    }

    @Override
    public User updateUser(User user) {
        user.setUpdateTime(new Date());
        userMapper1.updateUser(user);
        return userMapper1.findUserById(user.getId());
    }

    @Override
    public User findUserById(String uId) {
        return userMapper2.findUserById(uId);
    }

    @Override
    public List<User> findAllUser(Page page) {
        return userMapper1.findAllUser(page);
    }

    @Override
    public UserGroupVo findUserGroupVo(String uId) {
        return userMapper1.findUserGroupVo(uId);
    }

    @Override
    public List<User> findUserByCondition(User user) {
        List<User> userList = userMapper1.findUserByCondition(user);
        return userList;
    }

    @Override
    public List<User> findAllUser(String name, String gid) {
        return userMapper1.findAllUserTotal(name, gid);
    }
}
