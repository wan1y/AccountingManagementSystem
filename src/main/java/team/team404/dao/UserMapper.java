package team.team404.dao;

import org.apache.ibatis.annotations.Param;
import team.team404.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    int insertUser(@Param("username") String username, @Param("password") String password);

    int userUpdata(@Param("userDesc") String userDesc,@Param("username") String username, @Param("iconPath") String iconPath,@Param("userId") String userId);

    int pwdUpdata(@Param("username") String username, @Param("password") String password);
}