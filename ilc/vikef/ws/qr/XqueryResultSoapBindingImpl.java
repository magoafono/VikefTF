/**
 * XqueryResultSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package ilc.vikef.ws.qr;

import java.rmi.RemoteException;

import ilc.vikef.XQuery;


public class XqueryResultSoapBindingImpl implements ilc.vikef.ws.qr.IXqueryResult{
	
	static XQuery xquery;

	static{
		xquery = new XQuery();	
	}

    public java.lang.String evaluateXQuery(java.lang.String in0) throws java.rmi.RemoteException {
        return xquery.evaluateXQuery(in0);
    }

	public String evaluateXQuerySM(String in0) throws RemoteException {
        return xquery.evaluateXQuerySM(in0);
	}

	public String evaluateXQueryZ(String in0) throws RemoteException {
        return xquery.evaluateXQueryZ(in0);
	}

}
