package team.team404.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.team404.pojo.Data;

import java.util.Date;
import java.util.List;

public interface DataMapper {
    int deleteByPrimaryKey(@Param("no") Integer no);

    int insert(@Param("info") String info, @Param("money") Float money, @Param("time") Date time, @Param("userId") Integer userId);

    int insertSelective(Data record);

    Data selectByPrimaryKey(@Param("no") Integer no);

    int updateByPrimaryKey(@Param("no") Integer no, @Param("money") Float money, @Param("info") String info, @Param("time") Date time, @Param("userId") Integer userId);

    List<Data> selectAllByField(@Param("startPos") Integer startPos,@Param("pageSize") Integer pageSize,@Param("field") String field,@Param("order") String order,@Param("userId") Integer userId,@Param("username") String username);

    @Select(" select count(*) from data where user_id=#{userId} ")
    int dataCount(@Param("userId") Integer userId);

    int deleteByNos(@Param("ids") Integer[] ids);

    int updataStatus(@Param("status") Integer status,@Param("no") Integer no);

    List<Data> selectAllByCondition(@Param("moneymin") Integer moneymin, @Param("moneymax") Integer moneymax, @Param("info") String info, @Param("status") Integer status,@Param("startPos") Integer startPos,@Param("pageSize") Integer pageSize,@Param("userId") Integer userId,@Param("username") String username);

    List getIncome(@Param("userId") Integer userId,@Param("status")Integer status,@Param("year") Integer year);

    List getExpend(@Param("userId") Integer userId,@Param("status")Integer status,@Param("year") Integer year);


}