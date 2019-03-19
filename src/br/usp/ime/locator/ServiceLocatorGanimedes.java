package br.usp.ime.locator;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.usp.ime.util.Recursos;

public class ServiceLocatorGanimedes {

	private static ServiceLocatorGanimedes instance;
	private InitialContext ic;

	Recursos recursos = new Recursos();
	private String url = recursos.getResourceValue("wf-url");
	private String port = recursos.getResourceValue("wf-port");

	public static ServiceLocatorGanimedes getInstance() throws Exception {
		if (instance == null)
			instance = new ServiceLocatorGanimedes();

		return instance;
	}

	// Creates a new instance of ServiceLocator
	private ServiceLocatorGanimedes() throws Exception {
		// initialize the shared context object
		try {

			Properties props = new Properties();

			// glassfish
			// props.setProperty("org.omg.CORBA.ORBInitialHost", url);
			// props.setProperty("org.omg.CORBA.ORBInitialPort", port);
			//

			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			props.put("jboss.naming.client.ejb.context", true);
			ic = new InitialContext(props);

		} catch (NamingException ne) {
			throw new Exception("Unable to create " + "initial context", ne);
		}

	}

	// get an EJB from the cache or directory service
	public Object getRemoteEJB(String name) throws Exception {
		try {
			Object rRef = ic.lookup(name);

			return rRef;
		} catch (Exception ex) {
			throw new Exception("Unable to find EJB", ex);
		}
	}
}
