/**
 * ITradeFairVikefAnnotatorServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package ilc.vikef.ws;

public class ITradeFairVikefAnnotatorServiceLocator extends org.apache.axis.client.Service implements ilc.vikef.ws.ITradeFairVikefAnnotatorService {

    public ITradeFairVikefAnnotatorServiceLocator() {
    }


    public ITradeFairVikefAnnotatorServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ITradeFairVikefAnnotatorServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ItalianTradeFair
    /*
     * OCCHIO!!!! Quando si fa il jar per il server deve essere sempre usata la porta 8080 e non lo 81
     * //altrimenti il client si becca un bel NullPointerException 
     * */
  //private java.lang.String ItalianTradeFair_address = "http://vikef.ilc.cnr.it:81/axis/services/ItalianTradeFair";
  //private java.lang.String ItalianTradeFair_address = "http://vikef.ilc.cnr.it:8080/axis/services/ItalianTradeFair";
    private java.lang.String ItalianTradeFair_address = "http://localhost:8080/axis/services/ItalianTradeFair";

    public java.lang.String getItalianTradeFairAddress() {
        return ItalianTradeFair_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ItalianTradeFairWSDDServiceName = "ItalianTradeFair";

    public java.lang.String getItalianTradeFairWSDDServiceName() {
        return ItalianTradeFairWSDDServiceName;
    }

    public void setItalianTradeFairWSDDServiceName(java.lang.String name) {
        ItalianTradeFairWSDDServiceName = name;
    }

    public ilc.vikef.ws.ITradeFairVikefAnnotator getItalianTradeFair() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ItalianTradeFair_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getItalianTradeFair(endpoint);
    }

    public ilc.vikef.ws.ITradeFairVikefAnnotator getItalianTradeFair(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ilc.vikef.ws.ItalianTradeFairSoapBindingStub _stub = new ilc.vikef.ws.ItalianTradeFairSoapBindingStub(portAddress, this);
            _stub.setPortName(getItalianTradeFairWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setItalianTradeFairEndpointAddress(java.lang.String address) {
        ItalianTradeFair_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ilc.vikef.ws.ITradeFairVikefAnnotator.class.isAssignableFrom(serviceEndpointInterface)) {
                ilc.vikef.ws.ItalianTradeFairSoapBindingStub _stub = new ilc.vikef.ws.ItalianTradeFairSoapBindingStub(new java.net.URL(ItalianTradeFair_address), this);
                _stub.setPortName(getItalianTradeFairWSDDServiceName());
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
        if ("ItalianTradeFair".equals(inputPortName)) {
            return getItalianTradeFair();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:ITradeFairVikefAnnotator", "ITradeFairVikefAnnotatorService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:ITradeFairVikefAnnotator", "ItalianTradeFair"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ItalianTradeFair".equals(portName)) {
            setItalianTradeFairEndpointAddress(address);
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
