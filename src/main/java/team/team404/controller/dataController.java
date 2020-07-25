package team.team404.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team.team404.pojo.Result;
import team.team404.service.testService;

import javax.servlet.http.HttpServletRequest;

@Controller  //标记在类上面，表明当前类是Spring mvc Controller对象
@RequestMapping("/img")
public class dataController {

    @Autowired
    private testService testservice;
    @RequestMapping("/up")
    @ResponseBody

    public Result up(MultipartFile file, HttpServletRequest request){
        Result result = testservice.up(file,request);
        return result;
    }
}
