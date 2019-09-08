package cn.usts.pojo;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/8/1 23:18
 */

public class SysService {

    /** ID */
    private int id;

    /** UID对应的评论者ID */
    private int uId;

    /** 评论文字 */
    private String textarea;

    /** 星数 */
    private int rateCount;

    private String userToken;

    private boolean switchs;

    public SysService(){}

    public SysService(int id,int uId,String textarea,int rateCount,String userToken,boolean switchs){
        this.id = id;
        this.uId = uId;
        this.textarea = textarea;
        this.rateCount = rateCount;
        this.userToken = userToken;
        this.switchs = switchs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getTextarea() {
        return textarea;
    }

    public void setTextarea(String textarea) {
        this.textarea = textarea;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public boolean isSwitchs() {
        return switchs;
    }

    public void setSwitchs(boolean switchs) {
        this.switchs = switchs;
    }

    @Override
    public String toString() {
        return "SysService{" +
                "id=" + id +
                ", uId=" + uId +
                ", textarea='" + textarea + '\'' +
                ", rateCount=" + rateCount +
                ", userToken='" + userToken + '\'' +
                ", switchs=" + switchs +
                '}';
    }
}
