package br.usp.ime.ganimedes.view;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "messageBean")
@RequestScoped
public class MessageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ResourceBundle bundle = ResourceBundle.getBundle("messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

	public void addMessage(String msg, String componentID, FacesMessage.Severity severity) {
		msg = bundle.getString(msg);
		FacesMessage fm = new FacesMessage(severity, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(componentID, fm);
	}

	public void addCustomMessage(String msg, String componentID, FacesMessage.Severity severity) {
		FacesMessage fm = new FacesMessage(severity, msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(componentID, fm);
	}

	public String getMessage(String key) {
		String text = null;
		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " not found ??";
		}
		return text;
	}

}
