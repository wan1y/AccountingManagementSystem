package team.team404.controller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.team404.pojo.Result;
import team.team404.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller  //标记在类上面，表明当前类是Spring mvc Controller对象
@RequestMapping("/index")
public class home{

    @Autowired
    UserService userService;


    @RequestMapping("/main")
    public String main(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) {

        ValidateCode validateCode = new ValidateCode(120, 30, 4, 50);

        request.getSession().setAttribute("code", validateCode.getCode());
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password, String code, HttpServletRequest request){
        Result result = userService.login(username,password,code,request);

        return result;
    }


    @RequestMapping("/userReg")
    @ResponseBody
    public Result userReg(String username, String password, String code, HttpServletRequest request){

        Result result = userService.userReg(username,password,code,request);

        return result;
    }

    @RequestMapping("/userUpdata")
    @ResponseBody
    public Result userUpdata(String info,String username,String icon,String id){

        Result result = userService.userUpdata(info,username,icon,id);

        return result;


    }


    @RequestMapping("/pwdUpdata")
    @ResponseBody
    public Result pwdUpdata(String username, String password){

        Result result = userService.pwdUpdata(username,password);

        return result;
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session= request.getSession();
   //     session.getAttribute("user").
        session.removeAttribute("user");
        request.setAttribute("message","login out do it");
        return "login";
    }
}
