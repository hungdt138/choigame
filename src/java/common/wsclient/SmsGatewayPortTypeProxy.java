package common.wsclient;

public class SmsGatewayPortTypeProxy implements SmsGatewayPortType {
  private String _endpoint = null;
  private SmsGatewayPortType smsGatewayPortType = null;
  
  public SmsGatewayPortTypeProxy() {
    _initSmsGatewayPortTypeProxy();
  }
  
  public SmsGatewayPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSmsGatewayPortTypeProxy();
  }
  
  private void _initSmsGatewayPortTypeProxy() {
    try {
      smsGatewayPortType = (new SmsGatewayLocator()).getsmsGatewayPort();
      if (smsGatewayPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)smsGatewayPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)smsGatewayPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (smsGatewayPortType != null)
      ((javax.xml.rpc.Stub)smsGatewayPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SmsGatewayPortType getSmsGatewayPortType() {
    if (smsGatewayPortType == null)
      _initSmsGatewayPortTypeProxy();
    return smsGatewayPortType;
  }
  
  public java.lang.String smsGateway(java.lang.String user_ID, java.lang.String service_ID, java.lang.String command_Code, java.lang.String message, java.lang.String request_ID) throws java.rmi.RemoteException{
    if (smsGatewayPortType == null)
      _initSmsGatewayPortTypeProxy();
    return smsGatewayPortType.smsGateway(user_ID, service_ID, command_Code, message, request_ID);
  }
  
  
}