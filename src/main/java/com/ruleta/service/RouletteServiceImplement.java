package com.ruleta.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.ruleta.interfaces.IRouletteService;
import com.ruleta.model.Roulette;

@Service
public class RouletteServiceImplement implements IRouletteService {

	private List<Roulette> list = new ArrayList<Roulette>();

	@Override
	public List<Roulette> findAll() {
		return list;
	}

	@Override
	public String rouletteOpening(Integer idRoulette) {
		for (Roulette roulette : list) {
			if (roulette.getIdRoulette() == idRoulette) {
				roulette.setState(true);
				return "Ruleta Abierta";
			}
		}
		throw new com.ruleta.exception.NotFoundModelException("Ruleta no Encontrada");
	}

	@Override
	public Integer save(Roulette roulette) {
		roulette.setCreationDate(LocalDateTime.now());;
		if (roulette.getIdRoulette() == null) {
			throw new com.ruleta.exception.NotFoundModelException("Id no puede estar vacio");
		}
		if (roulette.getIdRoulette() == 0) {
			throw new com.ruleta.exception.NotFoundModelException("Id no puede ser 0");
		}
		for (Roulette obj : list) {
			if (obj.getIdRoulette() == roulette.getIdRoulette()) {
				throw new com.ruleta.exception.NotFoundModelException("Id ya registrado");
			}
		}
		list.add(roulette);
		return roulette.getIdRoulette();
	}

	@Override
	public void delete(Integer idRoulette) {
		for (Roulette roulette : list) {
			if(roulette.getIdRoulette() == idRoulette)
				throw new com.ruleta.exception.NotFoundModelException("Id no enontrado");
			if (roulette.getIdRoulette() == idRoulette) {
				list.remove(idRoulette);
			}
		}
	}

}
