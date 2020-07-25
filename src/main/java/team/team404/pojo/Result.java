package team.team404.pojo;

import java.util.List;

/*
 *
 * 封装返回信息，layui接口需求
 */
public class Result {

    private Integer code;

    private String msg;



    private  Integer count;

    private List data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
