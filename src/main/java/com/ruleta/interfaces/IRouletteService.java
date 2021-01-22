package com.ruleta.interfaces;

import java.util.List;

import com.ruleta.model.Bet;
import com.ruleta.model.Roulette;

public interface IRouletteService {
	List<Roulette> findAll();
	String rouletteOpening(Integer idRoulette);
	Integer save(Roulette roulette);
	List<Bet> closeRoulette(Integer idRoulette);
}
