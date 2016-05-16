package br.com.showMustGoOn.enums.converter;

import javax.persistence.AttributeConverter;

import br.com.showMustGoOn.enums.SexoEnum;

public class SexoEnumConverter implements AttributeConverter<SexoEnum, String> {

	@Override
	public String convertToDatabaseColumn(SexoEnum arg0) {
		return arg0.getChave();
	}

	@Override
	public SexoEnum convertToEntityAttribute(String arg0) {
		for (SexoEnum sexo : SexoEnum.values()) {
			if(sexo.getChave().equals(arg0)){
				return sexo;
			}
		}
		throw new IllegalArgumentException("Valor para enum não existe");
	}

}
