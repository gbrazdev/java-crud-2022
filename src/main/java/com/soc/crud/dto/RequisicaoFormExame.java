package com.soc.crud.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.soc.crud.models.Exame;
import com.soc.crud.models.StatusExame;







//É uma classe DTO (Data Transfer Object)
public class RequisicaoFormExame {
	
	
	@NotNull
	private StatusExame exame; // em caso de erro, NotBlank.requisicaoNovoExame.nome
	@NotBlank
	@NotNull
	@Column(unique=true) 
	private String nomepaciente;
	private Date data;
	private String observacao;

	/**
	 * @return the nome
	 */
	public StatusExame getExame() {
		return exame;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setExame(StatusExame exame) {
		this.exame = exame;
	}

	/**
	 * @return the nomepaciente
	 */
	public String getNomepaciente() {
		return nomepaciente;
	}

	/**
	 * @param nomepaciente the nomepaciente to set
	 */
	public void setNomepaciente(String nomepaciente) {
		this.nomepaciente = nomepaciente;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Exame toExame() {
		Exame exame = new Exame();
		exame.setExame(this.exame);
		exame.setNomepaciente(this.nomepaciente);
		exame.setData(this.data);
		exame.setObservacao(this.observacao);
		

		return exame;
	}

	public Exame toExame(Exame exame) {
		exame.setExame(this.exame);
		exame.setNomepaciente(this.nomepaciente);
		exame.setData(this.data);
		exame.setObservacao(this.observacao);
		return exame;
	}

	public void fromExame(Exame exame) {
		this.exame = exame.getExame();
		this.nomepaciente = exame.getNomepaciente();
		this.data = exame.getData();
		this.observacao = exame.getObservacao();

	}
	
	

	@Override
	public String toString() {
		return "RequisicaoNovoExame{" + "exame='" + exame + '\'' + ", "
				+ "nomepaciente=" + nomepaciente + ", "
				+ "data=" + data + ", "
				+ "observacao=" + observacao + 	
						 '}';
	}
}
