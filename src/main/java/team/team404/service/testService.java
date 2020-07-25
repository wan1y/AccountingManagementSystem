package team.team404.service;

import org.springframework.web.multipart.MultipartFile;
import team.team404.pojo.Data;
import team.team404.pojo.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface testService {

    Result getAllByField(Integer startPos,Integer pageSize,String field,String order,Integer userId,String username);
    Data getEdit(Integer no);
    Result deleteByNo(Integer no);
    Result deleteByNos(Integer[] ids);
    Result updataStatus(Integer status,Integer no);

    Result editData(Integer no, Float money, String info, Date time,Integer userId);

    Result addData(String info, Float money, Date time, Integer userId);

    Result getAllByCondition(Integer moneymin, Integer moneymax, String info, Integer status,Integer startPos,Integer pageSize,Integer userId,String username);

    Result up(MultipartFile file, HttpServletRequest request);

}
