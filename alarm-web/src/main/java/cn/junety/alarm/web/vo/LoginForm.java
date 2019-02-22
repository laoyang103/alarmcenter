package cn.junety.alarm.web.vo;

/**
 * Created by caijt on 2017/4/2.
 */
public class LoginForm {
    protected String username;
    protected String password;
    protected String wxopenid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
