package cn.junety.alarm.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cn.junety.alarm.server.syslog.SyslogServer;

/**
 * Created by caijt on 2017/3/4.
 */
@SpringBootApplication
public class Bootstrap {

  private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

  public static void main(String[] args) {
    try {
      SyslogServer syslog = new SyslogServer(5144);
      syslog.start();
      logger.info("start syslog server success");

      SpringApplication.run(Bootstrap.class);
      logger.info("start alarm server success");
    } catch (Exception e) {
      logger.error("start alarm server error, caused by", e);
    }
  }
}
