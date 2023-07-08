/**
 * OntologyManagerSoapServiceProviderServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ilc.vikef.ontologymanager;

public class OntologyManagerSoapServiceProviderServiceLocator extends org.apache.axis.client.Service implements ilc.vikef.ontologymanager.OntologyManagerSoapServiceProviderService {

    public OntologyManagerSoapServiceProviderServiceLocator() {
    }


    public OntologyManagerSoapServiceProviderServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OntologyManagerSoapServiceProviderServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OntologyManagerSoapServiceProvider
    private java.lang.String OntologyManagerSoapServiceProvider_address = "http://136.201.104.12:8080/ontologymanager/services/OntologyManagerSoapServiceProvider";

    public java.lang.String getOntologyManagerSoapServiceProviderAddress() {
        return OntologyManagerSoapServiceProvider_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OntologyManagerSoapServiceProviderWSDDServiceName = "OntologyManagerSoapServiceProvider";

    public java.lang.String getOntologyManagerSoapServiceProviderWSDDServiceName() {
        return OntologyManagerSoapServiceProviderWSDDServiceName;
    }

    public void setOntologyManagerSoapServiceProviderWSDDServiceName(java.lang.String name) {
        OntologyManagerSoapServiceProviderWSDDServiceName = name;
    }

    public ilc.vikef.ontologymanager.OntologyManagerSoapServiceProvider_PortType getOntologyManagerSoapServiceProvider() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OntologyManagerSoapServiceProvider_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOntologyManagerSoapServiceProvider(endpoint);
    }

    public ilc.vikef.ontologymanager.OntologyManagerSoapServiceProvider_PortType getOntologyManagerSoapServiceProvider(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ilc.vikef.ontologymanager.OntologyManagerSoapServiceProviderSoapBindingStub _stub = new ilc.vikef.ontologymanager.OntologyManagerSoapServiceProviderSoapBindingStub(portAddress, this);
            _stub.setPortName(getOntologyManagerSoapServiceProviderWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOntologyManagerSoapServiceProviderEndpointAddress(java.lang.String address) {
        OntologyManagerSoapServiceProvider_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ilc.vikef.ontologymanager.OntologyManagerSoapServiceProvider_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ilc.vikef.ontologymanager.OntologyManagerSoapServiceProviderSoapBindingStub _stub = new ilc.vikef.ontologymanager.OntologyManagerSoapServiceProviderSoapBindingStub(new java.net.URL(OntologyManagerSoapServiceProvider_address), this);
                _stub.setPortName(getOntologyManagerSoapServiceProviderWSDDServiceName());
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
        if ("OntologyManagerSoapServiceProvider".equals(inputPortName)) {
            return getOntologyManagerSoapServiceProvider();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:OntologyManager.IPSI.vikef", "OntologyManagerSoapServiceProviderService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:OntologyManager.IPSI.vikef", "OntologyManagerSoapServiceProvider"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OntologyManagerSoapServiceProvider".equals(portName)) {
            setOntologyManagerSoapServiceProviderEndpointAddress(address);
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
