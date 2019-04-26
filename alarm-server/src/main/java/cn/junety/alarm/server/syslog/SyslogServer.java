package cn.junety.alarm.server.syslog;

import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.junety.alarm.server.service.SyslogService;

public class SyslogServer extends Thread {

  private static final Logger logger = LoggerFactory.getLogger(SyslogServer.class);

  private static SyslogParser syslogParser = new SyslogParser();

  private SyslogService syslogService = new SyslogService();

  private DatagramSocket socket;
  private boolean running;
  private byte[] buf = new byte[256];

  public SyslogServer(int port) {
    try {
      socket = new DatagramSocket(port);
    } catch (Exception e) {
      e.printStackTrace();
    }           
  }

  public void run() {
    running = true;
    DatagramPacket packet = new DatagramPacket(buf, buf.length);

    while (running) {
      try {
        socket.receive(packet);
      } catch (Exception e) {
        e.printStackTrace();
      }           
      String sysstr = new String(packet.getData(), 0, packet.getLength());
      syslogService.addSyslogByString(packet.getAddress().getHostAddress(), packet.getPort(), sysstr);
    }
    socket.close();
  }
}
