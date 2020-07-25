package team.team404.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.team404.pojo.Money;
import team.team404.pojo.User;
import team.team404.service.resultService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/result")
public class resultController {

    @Autowired
    private resultService resultservice;

    @RequestMapping("/getIncome")
    @ResponseBody
    public List<Money> getIncome(Integer year,HttpServletRequest request){
        HttpSession session= request.getSession(false);
        Object obj = session.getAttribute("user");
        User user = (User) obj;
        Integer userId = user.getId();

        List<Money> list = resultservice.getIncome(userId,1,year);
        return list;
    }

    @RequestMapping("/getExpend")
    @ResponseBody
    public List<Money> getExpend(Integer year,HttpServletRequest request){
        HttpSession session= request.getSession(false);
        Object obj = session.getAttribute("user");
        User user = (User) obj;
        Integer userId = user.getId();

        List<Money> list = resultservice.getExpend(userId,0,year);
        return list;
    }
}
