/**
 * ItalianTradeFairSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package ilc.vikef.ws;

import java.rmi.RemoteException;

import ilc.vikef.TradeFairVikefAnnotator;

public class ItalianTradeFairSoapBindingImpl implements ilc.vikef.ws.ITradeFairVikefAnnotator{
	static TradeFairVikefAnnotator itaNLP;

	static{
		itaNLP = new TradeFairVikefAnnotator();	
	}
   public java.lang.String run(java.lang.String in0) throws java.rmi.RemoteException {
        return itaNLP.run(in0);
    }

    public java.lang.String runByURL(java.lang.String in0) throws java.rmi.RemoteException {
        return itaNLP.runByURL(in0);
    }

	public String runByURLSM(String in0) throws RemoteException {
        return itaNLP.runByURLSM(in0);
	}

	public String runByURLZ(String in0) throws RemoteException {
        return itaNLP.runByURLZ(in0);
	}

	public String runSM(String in0) throws RemoteException {
        return itaNLP.runSM(in0);
	}

	public String runZ(String in0) throws RemoteException {
        return itaNLP.runZ(in0);
	}


}
