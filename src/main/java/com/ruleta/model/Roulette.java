package com.ruleta.model;

import java.time.LocalDateTime;


public class Roulette {
	private Integer idRoulette;
	private LocalDateTime creationDate;
	private LocalDateTime closingDate;
	private Boolean state;
	
	public Integer getIdRoulette() {
		return idRoulette;
	}
	public void setIdRoulette(Integer idRoulette) {
		this.idRoulette = idRoulette;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public LocalDateTime getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(LocalDateTime closingDate) {
		this.closingDate = closingDate;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
}
