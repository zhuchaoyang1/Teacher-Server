package cn.usts.util;

/**
 * 用户交互结果集
 * @Author: ${朱朝阳}
 * @Date: 2019/7/14 23:15
 */

public class JSONBean {

    /** success or error */
    private String msg;

    /** response body */
    private Object data;

    public JSONBean(){}

    public JSONBean(String msg,Object data){
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JSONBean{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
