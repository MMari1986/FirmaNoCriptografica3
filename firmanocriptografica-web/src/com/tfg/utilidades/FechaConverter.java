package com.tfg.utilidades;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@SuppressWarnings("rawtypes")
@FacesConverter("FechaConverter")
public class FechaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String resultado;
		if(value != null) {
			resultado = value;
		} else {
			resultado = null;
		}
		if ( resultado == null ) {
			throw new ConverterException();
		}
		return resultado;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
