/**
 * IXqueryResultServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package ilc.vikef.ws.qr;

public class IXqueryResultServiceLocator extends org.apache.axis.client.Service implements ilc.vikef.ws.qr.IXqueryResultService {

    public IXqueryResultServiceLocator() {
    }


    public IXqueryResultServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IXqueryResultServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for XqueryResult
    //private java.lang.String XqueryResult_address = "http://vikef.ilc.cnr.it:81/axis/services/XqueryResult";
    private java.lang.String XqueryResult_address = "http://vikef.ilc.cnr.it:8080/axis/services/XqueryResult";

    public java.lang.String getXqueryResultAddress() {
        return XqueryResult_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String XqueryResultWSDDServiceName = "XqueryResult";

    public java.lang.String getXqueryResultWSDDServiceName() {
        return XqueryResultWSDDServiceName;
    }

    public void setXqueryResultWSDDServiceName(java.lang.String name) {
        XqueryResultWSDDServiceName = name;
    }

    public ilc.vikef.ws.qr.IXqueryResult getXqueryResult() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(XqueryResult_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getXqueryResult(endpoint);
    }

    public ilc.vikef.ws.qr.IXqueryResult getXqueryResult(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ilc.vikef.ws.qr.XqueryResultSoapBindingStub _stub = new ilc.vikef.ws.qr.XqueryResultSoapBindingStub(portAddress, this);
            _stub.setPortName(getXqueryResultWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setXqueryResultEndpointAddress(java.lang.String address) {
        XqueryResult_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ilc.vikef.ws.qr.IXqueryResult.class.isAssignableFrom(serviceEndpointInterface)) {
                ilc.vikef.ws.qr.XqueryResultSoapBindingStub _stub = new ilc.vikef.ws.qr.XqueryResultSoapBindingStub(new java.net.URL(XqueryResult_address), this);
                _stub.setPortName(getXqueryResultWSDDServiceName());
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
        if ("XqueryResult".equals(inputPortName)) {
            return getXqueryResult();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:IXqueryResult", "IXqueryResultService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:IXqueryResult", "XqueryResult"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("XqueryResult".equals(portName)) {
            setXqueryResultEndpointAddress(address);
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
