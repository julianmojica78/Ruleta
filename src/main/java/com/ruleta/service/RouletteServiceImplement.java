package com.ruleta.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.ruleta.interfaces.IRouletteService;
import com.ruleta.model.Bet;
import com.ruleta.model.Roulette;

@Service
public class RouletteServiceImplement implements IRouletteService {
	private List<Roulette> listRoulette = new ArrayList<Roulette>();
	private List<Bet> listBet = new ArrayList<Bet>();
	
	@PostConstruct
	private void init(){
		Roulette roulette = new Roulette();
		roulette.setIdRoulette(1);
		roulette.setCreationDate(LocalDateTime.now());
		roulette.setState("Cerrada");
		listRoulette.add(roulette);
	}

	@Override
	public List<Roulette> findAllRoulette() {
		return listRoulette;
	}

	@Override
	public String rouletteOpening(Integer idRoulette) {
		for (Roulette roulette : listRoulette) {
			if (roulette.getIdRoulette() == idRoulette && roulette.getState().equals("Cerrada")) {
				roulette.setState("Abierta");
				
				return "Ruleta Abierta";
			}
		}
		throw new com.ruleta.exception.NotFoundModelException("Ruleta no Encontrada");
	}

	@Override
	public Integer save(Roulette roulette) {
		validateIdRoulette(roulette.getIdRoulette());
		roulette.setCreationDate(LocalDateTime.now());
		roulette.setState("Cerrada");
		listRoulette.add(roulette);
		
		return roulette.getIdRoulette();
	}

	public String rouletteBet(int idRoulette, int idUser, int number, int money) {
		for (Roulette roulette : listRoulette) {
			validateBet(idRoulette, idUser, number, money, roulette);
			if (roulette.getIdRoulette() == idRoulette) {
				Bet bet = new Bet();
				bet.setIdRoulette(idRoulette);
				bet.setIdUser(idUser);
				bet.setIdNumber(number);
				bet.setMoney(money);
				listBet.add(bet);

				return "Apuesta Realizada Correctamente";
			}
		}
		throw new com.ruleta.exception.NotFoundModelException("Ruleta no existe");
	}

	@Override
	public List<Bet> closeRoulette(Integer idRoulette) {
		List<Bet> listSecondary = new ArrayList<Bet>();
		int random = (int) Math.floor(Math.random() * 36);
		for (Roulette roulette : listRoulette) {
			if (roulette.getIdRoulette() == 0 && roulette.getIdRoulette() == null
					&& roulette.getIdRoulette() == idRoulette)
				throw new com.ruleta.exception.NotFoundModelException("Id no enontrado");
			if (roulette.getIdRoulette() == idRoulette) {
				roulette.setClosingDate(LocalDateTime.now());
				roulette.setState("Cerrada");
			}
		}
		for (Bet bet : listBet) {
			if (bet.getIdRoulette() == idRoulette) {
				if (bet.getIdNumber() == random) {
					bet.setMoney(bet.getMoney() * 5);
				} else if (bet.getIdNumber() % 2 == 0 || bet.getIdNumber() % 2 == 1) {
					bet.setMoney((int) (bet.getMoney() * 1.8));
				} else {
					bet.setMoney(0);
				}
				listSecondary.add(bet);
			}
		}
		return listSecondary;
	}

	private void validateIdRoulette(Integer idRoulette) {
		if (idRoulette == null) {
			throw new com.ruleta.exception.NotFoundModelException("Id de la ruleta no puede estar vacio");
		}
		if (idRoulette <= 0) {
			throw new com.ruleta.exception.NotFoundModelException("Id de la ruleta no puede ser 0");
		}
		for (Roulette obj : listRoulette) {
			if (obj.getIdRoulette() == idRoulette) {
				throw new com.ruleta.exception.NotFoundModelException("Id de la ruleta ya registrado");
			}
		}
	}

	private void validateBet(Integer idRoulette, Integer idUser, Integer number, Integer money, Roulette roulette) {
		if (idRoulette == null) {
			throw new com.ruleta.exception.NotFoundModelException("Id de la ruleta no puede estar vacio");
		}
		if (idRoulette <= 0) {
			throw new com.ruleta.exception.NotFoundModelException("Id de la ruleta no puede ser 0");
		}
		if (roulette.getState().equals("Cerrada") && roulette.getIdRoulette() == idRoulette) {
			throw new com.ruleta.exception.NotFoundModelException("Ruleta se encuentra cerrada");
		}
		if (idUser <= 0 || number < 0 || number > 36) {
			throw new com.ruleta.exception.NotFoundModelException("Id usuario o Numero de apuesta incorrecto");
		}
		if (money <= 0 || money >= 10000) {
			throw new com.ruleta.exception.NotFoundModelException("Dinero No Cumple los Requisitos");
		}
	}

}
