package cn.junety.alarm.base.entity;

/**
 * Created by caijt on 2017/4/3.
 */
public class Device {
    private int id;
    private int userid;
    private String cpus;
    private String macs;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCpus() {
        return cpus;
    }
    public void setCpus(String cpus) {
        this.cpus = cpus;
    }

    public String getMacs() {
        return macs;
    }
    public void setMacs(String macs) {
        this.macs = macs;
    }
}
