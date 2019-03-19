package br.usp.ime.ganimedes.view;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class GenericConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value != null) {
			return component.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			component.getAttributes().put(value.toString(), value);
			return value.toString();
		}
		return "";
	}
}
