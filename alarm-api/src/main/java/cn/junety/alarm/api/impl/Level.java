package cn.junety.alarm.api.impl;

/**
 * Created by caijt on 2017/1/28.
 */
public enum Level {
    EMERGENCY("emergency"), ALERT("alert"), CRITICAL("critical"), ERROR("error"), WARNING("warning"), NOTICE("notice"), INFO("info"), DEBUG("debug");
    private String level;
    Level(String level) {
        this.level = level;
    }
    public String getLevel() {
        return level;
    }
}
