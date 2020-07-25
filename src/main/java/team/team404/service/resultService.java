package team.team404.service;

import team.team404.pojo.Money;

import java.util.List;

public interface resultService {
    List<Money> getIncome(Integer userId, Integer status,Integer year);

    List<Money> getExpend(Integer userId, Integer status,Integer year);

}
