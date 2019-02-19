package cn.junety.alarm.web.vo;

/**
 * Created by caijt on 2017/4/2.
 */
public class WxbindForm extends LoginForm {
    private String wxopenid;

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", wxopenid='" + wxopenid + '\'' +
                '}';
    }
}
