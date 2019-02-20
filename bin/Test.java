

import cn.junety.alarm.api.AlarmClient;

/**
 * Created by caijt on 2017/3/29.
 */
public class Test {
    public static void main(String[] args) {
        AlarmClient.debug(1, "alarm.web.*", "WEB应用崩了");
        // AlarmClient.debug(2, "com.youfu.db", "数据库崩了");
    }
}
