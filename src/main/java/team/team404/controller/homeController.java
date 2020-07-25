package team.team404.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.team404.pojo.Data;
import team.team404.pojo.Result;
import team.team404.pojo.User;
import team.team404.service.testService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/test")
public class homeController {


    @Autowired
    private testService testservice;

    @RequestMapping("/userdata")
    public String userdata(){

        return "userdata";
    }

    @RequestMapping("/getAll")
    @ResponseBody   //自动将数据转换成json格式   避免被视图解析器解析
    public Result getAll(Integer page,Integer limit,String field,String order,HttpServletRequest request){
        HttpSession session= request.getSession(false);
        Object obj = session.getAttribute("user");
        User user = (User) obj;
        Integer userId = user.getId();
        String username = user.getUsername();
        if(field==null){
            field="no";
        }
        if (order==null){
            order="desc";
        }
        Result result = testservice.getAllByField(page,limit,field,order,userId,username);
        return result;
    }

    @RequestMapping("/edit")
    @ResponseBody   //自动将数据转换成json格式   避免被视图解析器解析
    public Result edit(Integer no, Float money, String info, String time, Integer userId){
        Date time1 = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //使用SimpleDateFormat的parse()方法生成Date
            time1 = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Result result = testservice.editData(no,money,info,time1,userId);
        return result;
    }

    @RequestMapping("/deleteByNo")
    @ResponseBody
    public Result deleteById(Integer no){
        Result result = testservice.deleteByNo(no);
        return result;
    }


    @RequestMapping("/updataStatus")
    @ResponseBody
    public Result updataStatus(Integer status,Integer no){
        Result result = testservice.updataStatus(status,no);
        return result;
    }

    @RequestMapping("/deleteByNos")
    @ResponseBody
    public Result deleteByNos(Integer[] ids){
        Result result = testservice.deleteByNos(ids);
        return result;
    }
    @RequestMapping("/getEdit")
    @ResponseBody
    public Data getEdit(Integer no){
        Data data = testservice.getEdit(no);
        return data;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(String info,Float money,String time,HttpServletRequest request){
        HttpSession session= request.getSession(false);
        Object obj = session.getAttribute("user");
        User user = (User) obj;
        Integer userId = user.getId();
        Date time1 = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //使用SimpleDateFormat的parse()方法生成Date
            time1 = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Result result = testservice.addData(info,money,time1,userId);
        return result;
    }


    @RequestMapping("/getSomeData")
    @ResponseBody   //自动将数据转换成json格式   避免被视图解析器解析
    public Result getSomeData(Integer moneymin, Integer moneymax, String info, Integer status, Integer page,Integer limit,HttpServletRequest request){

        HttpSession session= request.getSession(false);
        Object obj = session.getAttribute("user");
        User user = (User) obj;
        Integer userId = user.getId();
        String username = user.getUsername();
        Result result = testservice.getAllByCondition(moneymin,moneymax,info,status,page,limit,userId,username);
        return result;
    }

    @RequestMapping("/pwdUpdata")
    public String pwdUpdata(){
        return "pwdUpdata";
    }

}
