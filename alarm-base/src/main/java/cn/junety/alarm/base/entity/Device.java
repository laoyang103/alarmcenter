package cn.junety.alarm.base.entity;

/**
 * Created by caijt on 2017/4/3.
 */
public class Device {
  private int id;
  private int userid;
  private String devname;
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
  private String validTerm;
  private String manyTerm;
  private String serverTerm;
  private String clientTerm;
  private String httpTerm;
  private String mysqlTerm;
  private String oracleTerm;
  private String sqlserverTerm;
  private String urlTerm;
  private String messageTerm;
  private String storeTerm;
  private String mapTerm;
  private String topoTerm;
  private String pairTerm;
  private String diggerTerm;

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

  public String getDevname() {
    return devname;
  }
  public void setDevname(String devname) {
    this.devname = devname;
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
  public String getValidTerm() {
    return validTerm;
  }
  public void setValidTerm(String validTerm) {
    this.validTerm = validTerm;
  }
  public String getManyTerm() {
    return manyTerm;
  }
  public void setManyTerm(String manyTerm) {
    this.manyTerm = manyTerm;
  }
  public String getServerTerm() {
    return serverTerm;
  }
  public void setServerTerm(String serverTerm) {
    this.serverTerm = serverTerm;
  }
  public String getClientTerm() {
    return clientTerm;
  }
  public void setClientTerm(String clientTerm) {
    this.clientTerm = clientTerm;
  }
  public String getHttpTerm() {
    return httpTerm;
  }
  public void setHttpTerm(String httpTerm) {
    this.httpTerm = httpTerm;
  }
  public String getMysqlTerm() {
    return mysqlTerm;
  }
  public void setMysqlTerm(String mysqlTerm) {
    this.mysqlTerm = mysqlTerm;
  }
  public String getOracleTerm() {
    return oracleTerm;
  }
  public void setOracleTerm(String oracleTerm) {
    this.oracleTerm = oracleTerm;
  }
  public String getSqlserverTerm() {
    return sqlserverTerm;
  }
  public void setSqlserverTerm(String sqlserverTerm) {
    this.sqlserverTerm = sqlserverTerm;
  }
  public String getUrlTerm() {
    return urlTerm;
  }
  public void setUrlTerm(String urlTerm) {
    this.urlTerm = urlTerm;
  }
  public String getMessageTerm() {
    return messageTerm;
  }
  public void setMessageTerm(String messageTerm) {
    this.messageTerm = messageTerm;
  }
  public String getStoreTerm() {
    return storeTerm;
  }
  public void setStoreTerm(String storeTerm) {
    this.storeTerm = storeTerm;
  }
  public String getMapTerm() {
    return mapTerm;
  }
  public void setMapTerm(String mapTerm) {
    this.mapTerm = mapTerm;
  }
  public String getTopoTerm() {
    return topoTerm;
  }
  public void setTopoTerm(String topoTerm) {
    this.topoTerm = topoTerm;
  }
  public String getPairTerm() {
    return pairTerm;
  }
  public void setPairTerm(String pairTerm) {
    this.pairTerm = pairTerm;
  }
  public String getDiggerTerm() {
    return diggerTerm;
  }
  public void setDiggerTerm(String diggerTerm) {
    this.diggerTerm = diggerTerm;
  }

  public String getModString() {
    return 
      "\r\nvalidterm="      + validTerm +
      "\r\nmanyWatchpoint=" + manyWatchpoint +
      "\r\nmanyTerm="       + manyTerm +
      "\r\nmaxFlow="        + maxFlow +
      "\r\nserver="         + server +
      "\r\nserverTerm="     + serverTerm +
      "\r\nclient="         + client +
      "\r\nclientTerm="     + clientTerm +
      "\r\nhttp="           + http +
      "\r\nhttpTerm="       + httpTerm +
      "\r\nmysql="          + mysql +
      "\r\nmysqlTerm="      + mysqlTerm +
      "\r\noracle="         + oracle +
      "\r\noracleTerm="     + oracleTerm +
      "\r\nsqlserver="      + sqlserver +
      "\r\nsqlserverTerm="  + sqlserverTerm +
      "\r\nurl="            + url +
      "\r\nurlTerm="        + urlTerm +
      "\r\nmessage="        + message +
      "\r\nmessageTerm="    + messageTerm +
      "\r\nflowStorage="    + flowStorage +
      "\r\nstoreTerm="      + storeTerm +
      "\r\nmap="            + map +
      "\r\nmapTerm="        + mapTerm +
      "\r\ntopo="           + topo +
      "\r\ntopoTerm="       + topoTerm +
      "\r\ntrafficPair="    + trafficPair +
      "\r\npairTerm="       + pairTerm +
      "\r\ndigger="         + digger +
      "\r\ndiggerTerm="     + diggerTerm;
  }
}
