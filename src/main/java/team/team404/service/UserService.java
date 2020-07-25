package team.team404.service;

import org.apache.ibatis.annotations.Param;
import team.team404.pojo.Result;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    Result login(String username, String password, String code, HttpServletRequest request);

    Result userReg(String username, String password, String code, HttpServletRequest request);

    Result userUpdata(@Param("userDesc") String userDesc, @Param("username") String username, @Param("iconPath") String iconPath, @Param("userId") String userId);
    Result pwdUpdata(String username, String password);
}
