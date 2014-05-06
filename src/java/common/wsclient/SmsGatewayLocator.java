/**
 * SmsGatewayLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package common.wsclient;

public class SmsGatewayLocator extends org.apache.axis.client.Service implements SmsGateway {

    public SmsGatewayLocator() {
    }


    public SmsGatewayLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SmsGatewayLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for smsGatewayPort
    private java.lang.String smsGatewayPort_address = "http://game.moba.vn/SmsService/index.php";

    public java.lang.String getsmsGatewayPortAddress() {
        return smsGatewayPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String smsGatewayPortWSDDServiceName = "smsGatewayPort";

    public java.lang.String getsmsGatewayPortWSDDServiceName() {
        return smsGatewayPortWSDDServiceName;
    }

    public void setsmsGatewayPortWSDDServiceName(java.lang.String name) {
        smsGatewayPortWSDDServiceName = name;
    }

    public SmsGatewayPortType getsmsGatewayPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(smsGatewayPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsmsGatewayPort(endpoint);
    }

    public SmsGatewayPortType getsmsGatewayPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SmsGatewayBindingStub _stub = new SmsGatewayBindingStub(portAddress, this);
            _stub.setPortName(getsmsGatewayPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsmsGatewayPortEndpointAddress(java.lang.String address) {
        smsGatewayPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SmsGatewayPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                SmsGatewayBindingStub _stub = new SmsGatewayBindingStub(new java.net.URL(smsGatewayPort_address), this);
                _stub.setPortName(getsmsGatewayPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("smsGatewayPort".equals(inputPortName)) {
            return getsmsGatewayPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:message", "smsGateway");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:message", "smsGatewayPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("smsGatewayPort".equals(portName)) {
            setsmsGatewayPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
