package cn.usts.pojo.vo;

import cn.usts.pojo.FormData;

/**
 * 对FormData扩展 userToken
 *
 * @Author: ${朱朝阳}
 * @Date: 2019/7/17 10:26
 */

public class FormToken {

    private String userToken;

    private FormData formData;

    private String strTime;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public FormData getFormData() {
        return formData;
    }

    public void setFormData(FormData formData) {
        this.formData = formData;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }
}
