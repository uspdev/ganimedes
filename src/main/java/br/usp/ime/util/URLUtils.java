package br.usp.ime.util;

import java.net.URI;
import java.net.URISyntaxException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class URLUtils {

	public static String getURL() {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			URI uri;
			uri = new URI(request.getRequestURL().toString());
			URI newUri;
			newUri = new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), request.getContextPath().toString(), null, null);
			return newUri.toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}
