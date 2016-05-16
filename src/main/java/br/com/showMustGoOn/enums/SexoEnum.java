package br.com.showMustGoOn.enums;

public enum SexoEnum {
	MASCULINO("M","Masculino"),FEMININO("F","Feminino");
	
	private String chave;
	private String descricao;
	
	private SexoEnum(String chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public String getChave() {
		return chave;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
