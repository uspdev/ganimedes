package br.usp.ime.ganimedes.view;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.usp.ime.ganimedes.model.Empresa;

@FacesConverter(value = "empresaConverter", forClass = Empresa.class)
public class EmpresaConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

		if (value != null && !"".equals(value)) {
			Empresa entity = (Empresa) value;

			if (entity.getId() != null) {
				this.addAttribute(component, entity);

				if (entity.getId() != null) {
					return String.valueOf(entity.getId());
				}
				return (String) value;
			}
		}
		return "";
	}

	private void addAttribute(UIComponent component, Empresa o) {
		this.getAttributesFrom(component).put(o.getId().toString(), o);
	}

	private Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
}
