package cn.junety.alarm.base.entity;

/**
 * Created by caijt on 2017/4/3.
 */
public class Device {
  private int id;
  private int userid;
  private String cpus;
  private String macs;
  private int maxFlow;
  private int manyWatchpoint;
  private int server;
  private int client;
  private int http;
  private int mysql;
  private int oracle;
  private int sqlserver;
  private int url;
  private int message;
  private int flowStorage;
  private int map;
  private int topo;
  private int trafficPair;
  private int digger;

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
  public void setMaxFlow(int maxFlow) {
    this.maxFlow = maxFlow;
  }
  public int getMaxFlow() {
    return maxFlow;
  }

  public void setManyWatchpoint(int manyWatchpoint) {
    this.manyWatchpoint = manyWatchpoint;
  }
  public int getManyWatchpoint() {
    return manyWatchpoint;
  }

  public void setServer(int server) {
    this.server = server;
  }
  public int getServer() {
    return server;
  }

  public void setClient(int client) {
    this.client = client;
  }
  public int getClient() {
    return client;
  }

  public void setHttp(int http) {
    this.http = http;
  }
  public int getHttp() {
    return http;
  }

  public void setMysql(int mysql) {
    this.mysql = mysql;
  }
  public int getMysql() {
    return mysql;
  }

  public void setOracle(int oracle) {
    this.oracle = oracle;
  }
  public int getOracle() {
    return oracle;
  }

  public void setSqlserver(int sqlserver) {
    this.sqlserver = sqlserver;
  }
  public int getSqlserver() {
    return sqlserver;
  }

  public void setUrl(int url) {
    this.url = url;
  }
  public int getUrl() {
    return url;
  }

  public void setMessage(int message) {
    this.message = message;
  }
  public int getMessage() {
    return message;
  }

  public void setFlowStorage(int flowStorage) {
    this.flowStorage = flowStorage;
  }
  public int getFlowStorage() {
    return flowStorage;
  }

  public void setMap(int map) {
    this.map = map;
  }
  public int getMap() {
    return map;
  }

  public void setTopo(int topo) {
    this.topo = topo;
  }
  public int getTopo() {
    return topo;
  }

  public void setTrafficPair(int trafficPair) {
    this.trafficPair = trafficPair;
  }
  public int getTrafficPair() {
    return trafficPair;
  }

  public void setDigger(int digger) {
    this.digger = digger;
  }
  public int getDigger() {
    return digger;
  }
}
