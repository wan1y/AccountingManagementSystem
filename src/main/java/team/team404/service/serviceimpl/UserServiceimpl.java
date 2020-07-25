package team.team404.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.team404.dao.UserMapper;
import team.team404.pojo.Result;
import team.team404.pojo.User;
import team.team404.service.UserService;
import team.team404.util.Md5Utils;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Result login(String username, String password, String code, HttpServletRequest request) {
        Result result = new Result();
        String codeValue = (String)request.getSession().getAttribute("code");
        if(codeValue.equalsIgnoreCase(code)){
            User user = userMapper.selectByUsername(username);
            if(user == null || !(user.getPassword().equals(Md5Utils.encryption("team404",password)))){
                result.setCode(500);
                result.setMsg("用户名或者密码错误！");
            }else{
                result.setCode(200);
                result.setMsg("登录成功!");
                request.getSession().setAttribute("user", user);
            }
        }else{
            result.setCode(500);
            result.setMsg("验证码错误！");
        }
        return result;
    }


    public Result userReg(String username, String password, String code, HttpServletRequest request) {
        User user;
        Result result = new Result();
        String codeValue = (String)request.getSession().getAttribute("code");
        if(codeValue.equalsIgnoreCase(code)){
                    user = userMapper.selectByUsername(username);
                    if(user != null){
                        result.setCode(500);
                        result.setMsg("用户名已存在！");
                    }else{
                        int i = userMapper.insertUser(username,(Md5Utils.encryption("team404",password)));
                        user = userMapper.selectByUsername(username);
                        if(i > 0){
                            result.setCode(200);
                            result.setMsg("注册成功!");
                            request.getSession().setAttribute("user", user);
                        }else {
                            result.setCode(500);
                            result.setMsg("注册失败，请重新输入！");
                        }
                    }
        }else{
            result.setCode(500);
            result.setMsg("验证码错误！");
        }
        return result;
    }

    public Result userUpdata(String userDesc, String username, String iconPath,String userId) {
        Result result = new Result();

        int i =  userMapper.userUpdata(userDesc,username, iconPath,userId);
        if(i > 0){
            result.setCode(200);
            result.setMsg("更改成功！");
        }else{
            result.setCode(500);
            result.setMsg("更改失败！");
        }
        return result;
    }

    public Result pwdUpdata(String username, String password) {
        Result result = new Result();
        password = (Md5Utils.encryption("team404",password));
        int i = userMapper.pwdUpdata(username,password);
        if(i > 0){
            result.setCode(200);
            result.setMsg("更改成功！");
        }else{
            result.setCode(500);
            result.setMsg("更改失败！");
        }
        return result;


    }


}

