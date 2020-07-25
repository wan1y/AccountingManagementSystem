package team.team404.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.team404.dao.DataMapper;
import team.team404.pojo.Money;
import team.team404.service.resultService;

import java.util.List;

@Service
public class resultServiceImpl implements resultService {

    @Autowired
    private DataMapper dataMapper;


    public List<Money> getIncome(Integer userId, Integer status,Integer year) {
        List<Money> list = dataMapper.getIncome(userId,status,year);
        return list;
    }

    public List<Money> getExpend(Integer userId, Integer status,Integer year) {
        List<Money> list = dataMapper.getExpend(userId,status,year);
        return list;
    }

}
