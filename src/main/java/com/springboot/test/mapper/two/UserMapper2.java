package com.springboot.test.mapper.two;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.springboot.test.model.Page;
import com.springboot.test.model.po.User;
import com.springboot.test.model.vo.UserGroupVo;

import java.util.List;

@Mapper
public interface UserMapper2 {

    @Insert("insert into user(fid, name, age, gid, sex, address, creatTime, updateTime) values(#{fid}, #{name}, #{age}, #{gid}, #{sex}, #{address}, #{creatTime}, #{updateTime})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertUser(User user);

    @Delete("delete from user where fid = #{uId}")
    Integer deleteUser(String uId);

    @Update("update user set name=#{name}, age=#{age}, gid=#{gid}, sex=#{sex}, address=#{address}, updateTime=#{updateTime} where fid=#{fid}")
    int updateUser(User user);

    @Select("select * from user where fid = #{uId}")
    User findUserById(String uId);

    @Select("select * from user limit #{pageSize} offset #{pageSize} * #{pageNo}")
    List<User> findAllUser(Page page);

    @Select("select u.fid, u.name, u.age, g.name as groupName, g.id as gid from user u left join t_group g on u.gid=g.id where u.fid=#{id}")
    @Results({
            @Result(property = "uid",  column = "fid", javaType = String.class),
            @Result(property = "groupName",  column = "groupName", javaType = String.class),
    })
    UserGroupVo findUserGroupVo(String id);

    @Select("select * from user where gid=#{gid}")
    List<User> findUserByGid(String gid);


    /**
     * 根据id age gid 查找用户
     */
    @SelectProvider(type = mybatisSql.class, method = "findUserByCondition")
    List<User> findUserByCondition(User user);

    @SelectProvider(type = mybatisSql.class, method = "findAllUserTotal")
    List<User> findAllUserTotal(String name, String gid);


    class mybatisSql {
        public String findUserByCondition(User user) {

            return new SQL(){{
                SELECT("*");
                FROM("user");
                if(user.getId() != null) {
                    WHERE("fid = #{fid}");
                }
                if(user.getAge() != null) {
                    WHERE("age = #{age}");
                }
                if(user.getGid() != null) {
                    WHERE("gid = #{gid}");
                }

            }}.toString();
        };

        public String findAllUserTotal(String name, String gid) {

            return new SQL(){{
                SELECT("*");
                FROM("user");
                if(!StringUtils.isEmpty(name)) {
                    WHERE("name like #{name}");
                }
                if(!StringUtils.isEmpty(gid)) {
                    WHERE("gid = #{gid}");
                }
            }}.toString();
        }
    }

}
