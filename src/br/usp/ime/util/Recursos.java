package br.usp.ime.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Recursos extends ClassLoader {

	public String getOSsoUrl() {
		String url = null;

		// ClassLoader loader = getSystemClassLoader();
		Properties properties = new Properties();

		try {

			URL in = getClass().getResource("/system.properties");
			InputStream is = in.openStream();

			if (in != null) {
				properties.load(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		url = properties.getProperty("osso-url");
		return url;
	}

	public String getResourceValue(String name) {
		String valor = null;

		// ClassLoader loader = getSystemClassLoader();
		Properties properties = new Properties();

		try {

			URL in = getClass().getResource("/system.properties");
			InputStream is = in.openStream();

			if (in != null) {
				properties.load(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		valor = properties.getProperty(name);

		return valor;
	}

}
