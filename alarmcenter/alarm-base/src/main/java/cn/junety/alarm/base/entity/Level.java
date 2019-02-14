package cn.junety.alarm.base.entity;

/**
 * Created by caijt on 2017/1/28.
 */
public enum Level {
    DEBUG("debug"), INFO("info"), ERROR("error");

    private String value;

    Level(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "value='" + value + '\'' +
                '}';
    }
}
