 package com.springboot.test.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.test.annotation.Secret;
import com.springboot.test.model.vo.BaseVO;
import com.springboot.test.model.vo.ResultVO;
import com.springboot.test.model.vo.UserVO;

@Secret(BaseVO.class)                             //接口参数和返回要进行加解密
@RestController
@RequestMapping("user")
public class UserController {
    
    //采用内部类的实例代码块方式初始化map
    Map<Integer, UserVO> userMap = new HashMap<Integer, UserVO>(){
        private static final long serialVersionUID = -5303894088236344897L;
        {
            put(1,new UserVO(1,"张三"));
            put(2,new UserVO(2,"李四"));
            put(3,new UserVO(3,"王五"));
        }
    };
 
    // 通过id查询用户
    @GetMapping("getUserName/{id}")
    public ResultVO getUserName(@PathVariable("id")  Integer id){
        return new ResultVO(0,"查询成功",userMap.get(id));
    }
 
    // 通过name查询用户id
    @GetMapping("getUserId")
    public ResultVO getUserId(@RequestParam  String name){
        Iterator<Map.Entry<Integer, UserVO>> iterator = userMap.entrySet().iterator();
        UserVO u = null;
        while (iterator.hasNext()){
            Map.Entry<Integer, UserVO> entry = iterator.next();
            if(entry.getValue().getName().equals(name)){
                u = entry.getValue();
                break;
            }
        }
        return new ResultVO(0,"查询成功",u);
    }
 
    // 新增用户
    @PostMapping("addUser")
    public ResultVO addUser(@RequestBody UserVO user){
        return new ResultVO(0,"新增成功",user);
    }
 
    // 更改用户
    @PostMapping("updateUser")
    public ResultVO updateUser(@RequestBody UserVO user) throws Throwable {
        if(user==null||user.getId()==null){
            throw new NullPointerException();
        }else{
            return new ResultVO(0,"修改成功",user);
        }
    }
}
