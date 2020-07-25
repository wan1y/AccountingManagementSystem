package team.team404.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import team.team404.dao.DataMapper;
import team.team404.pojo.Data;
import team.team404.pojo.Result;
import team.team404.service.testService;
import team.team404.util.UpUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class testServiceImpl implements testService {

    @Autowired
    private DataMapper dataMapper;


    public Result getAllByField(Integer startPos,Integer pageSize,String field,String order,Integer userId,String username) {

        startPos = (startPos-1)*pageSize;
        List<Data> list = dataMapper.selectAllByField(startPos,pageSize,field,order,userId,username);

        int datacount = dataMapper.dataCount(userId);
        Result result = new Result();
        result.setCode(0);
        result.setData(list);
        result.setCount(datacount);
        return result;
    }

    public Data getEdit(Integer no) {
        Result result = new Result();
        Data data = dataMapper.selectByPrimaryKey(no);

        return data;
    }

    @Transactional
    public Result deleteByNo(Integer no) {
        Result result = new Result();
        try{
            int i = dataMapper.deleteByPrimaryKey(no);
            result.setMsg("删除成功");
            result.setCode(200);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("页面繁忙，请稍后重试");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(500);


            //设置手动回滚，因为服务层设置了事务，try除了抛出运行时异常，事务都不会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }
    }


    @Transactional
    public Result deleteByNos(Integer[] ids) {
        Result result = new Result();
        try{
            for(int i=0;i<ids.length;i++){
                int n = dataMapper.deleteByPrimaryKey(ids[i]);
            }
            result.setMsg("删除成功");
            result.setCode(200);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("页面繁忙，请稍后重试");
            result.setCode(500);
            return result;
        }
    }

    @Transactional
    public Result updataStatus(Integer status,Integer no) {

        int i = dataMapper.updataStatus(status,no);
        Result result = new Result();
        System.out.println(i);
        if(i>0){
            result.setMsg("修改成功");
        }
        else{
            result.setMsg("修改失败");
        }
        return result;
    }

    public Result editData(Integer no, Float money, String info, Date time,Integer userId) {
        int i = dataMapper.updateByPrimaryKey(no,money,info,time,userId);
        Result result = new Result();
        if(i>0){
            result.setMsg("修改成功");
            result.setCode(200);
        }
        else{
            result.setMsg("修改失败");
        }
        return result;
    }

    public Result addData(String info, Float money, Date time, Integer userId) {
        int i = dataMapper.insert(info,money,time,userId);
        Result result = new Result();
        if(i>0){
            result.setMsg("添加成功");
            result.setCode(200);
        }
        else{
            result.setMsg("添加失败");
        }
        return result;
    }

    public Result getAllByCondition(Integer moneymin, Integer moneymax, String info, Integer status,Integer startPos,Integer pageSize,Integer userId,String username) {
        startPos = (startPos-1)*pageSize;
        List<Data> list = dataMapper.selectAllByCondition(moneymin,moneymax,info,status,startPos,pageSize,userId,username);
        int n = list.size();
        Result result = new Result();
        result.setCode(0);
        result.setData(list);
        result.setCount(n);
        return result;
    }

    public Result up(MultipartFile file, HttpServletRequest request) {

        String iconPath = UpUtils.upfile(file,request);
        Result result = new Result();
        result.setMsg(iconPath);
        return result;
    }
}
