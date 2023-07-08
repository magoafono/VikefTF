/**
 * IXqueryResultService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package ilc.vikef.ws.qr;

public interface IXqueryResultService extends javax.xml.rpc.Service {
    public java.lang.String getXqueryResultAddress();

    public ilc.vikef.ws.qr.IXqueryResult getXqueryResult() throws javax.xml.rpc.ServiceException;

    public ilc.vikef.ws.qr.IXqueryResult getXqueryResult(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
