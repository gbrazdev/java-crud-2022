package com.soc.crud.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Exame {
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private StatusExame exame;
	    @Column(nullable = false, unique=true)
	    
	    private String nomepaciente;
	    private Date data;
	    private String observacao;
	    

	    public Exame() {  }

	    public Exame(StatusExame exame, String nomepaciente, Date data, String observacao) {
	        this.exame = exame;
	        this.nomepaciente = nomepaciente;
	        this.data = data;
	        this.observacao = observacao;
	        
	    }
	    
	        

		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

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

		@Override
	    public String toString() {
	        return "Exame{" +
	               "id=" + id +
	               ", exame='" + exame + '\'' +
	               ", nomepaciente=" + nomepaciente +
	               ", data=" + data +
	               ", observacao=" + observacao +
	               '}';
	    }
}
