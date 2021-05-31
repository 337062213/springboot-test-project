package com.springboot.test.service.impl;

import com.springboot.test.mapper.UserMapper;
import com.springboot.test.model.Page;
import com.springboot.test.model.api.User;
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
    private UserMapper userMapper;

    @Override
    public User insertUser(User user) {
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        Date date = new Date();
        user.setCreatTime(date);
        user.setUpdateTime(date);
        userMapper.insertUser(user);
        return userMapper.findUserById(user.getId());
    }

    @Override
    public Integer deleteUser(String uId) {
        return userMapper.deleteUser(uId);
    }

    @Override
    public User updateUser(User user) {
        user.setUpdateTime(new Date());
        userMapper.updateUser(user);
        return userMapper.findUserById(user.getId());
    }

    @Override
    public User findUserById(String uId) {
        return userMapper.findUserById(uId);
    }

    @Override
    public List<User> findAllUser(Page page) {
        return userMapper.findAllUser(page);
    }

    @Override
    public UserGroupVo findUserGroupVo(String uId) {
        return userMapper.findUserGroupVo(uId);
    }

    @Override
    public List<User> findUserByCondition(User user) {
        List<User> userList = userMapper.findUserByCondition(user);
        return userList;
    }

    @Override
    public List<User> findAllUser(String name, String gid) {
        return userMapper.findAllUserTotal(name, gid);
    }
}
