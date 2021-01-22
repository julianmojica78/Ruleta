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

	private List<Roulette> list = new ArrayList<Roulette>();
	private List<Bet> listBet = new ArrayList<Bet>();

	@Override
	public List<Roulette> findAll() {
		return list;
	}

	@Override
	public String rouletteOpening(Integer idRoulette) {
		for (Roulette roulette : list) {
			if (roulette.getIdRoulette() == idRoulette && roulette.getState().equals("Cerrada")) {
				roulette.setState("Abierta");
				return "Ruleta Abierta";
			}
		}
		throw new com.ruleta.exception.NotFoundModelException("Ruleta no Encontrada");
	}

	@Override
	public Integer save(Roulette roulette) {
		roulette.setCreationDate(LocalDateTime.now());
		roulette.setState("Cerrada");
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

	public String rouletteBet(int idRoulette, int idUser, int number, int money) {
		for (Roulette roulette : list) {
			if (idUser == 0 && number == 0 || number >= 36 ) {
				throw new com.ruleta.exception.NotFoundModelException("Id usuario o Numero de apuesta incorrecto");
			}
			if (idRoulette == 0) {
				throw new com.ruleta.exception.NotFoundModelException("Ruleta no Existe");
			}
			if (roulette.getState().equals("Cerrada") && roulette.getIdRoulette() == idRoulette) {
				throw new com.ruleta.exception.NotFoundModelException("Ruleta se encuentra cerrada");
			}
			if (money == 0 || money >= 10000) {
				throw new com.ruleta.exception.NotFoundModelException("Dinero No Cumple los Requisitos");
			}
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
		for (Roulette roulette : list) {
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

}
