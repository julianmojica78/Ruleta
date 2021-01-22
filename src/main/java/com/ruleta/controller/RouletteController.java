package com.ruleta.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruleta.interfaces.IRouletteService;
import com.ruleta.model.Bet;
import com.ruleta.model.Roulette;
import com.ruleta.service.RouletteServiceImplement;

@RestController
@RequestMapping("/roulettes")
public class RouletteController {
	
	@Autowired
	private RouletteServiceImplement service;
	
	@GetMapping("/listRoulette")
	public ResponseEntity<List<Roulette>> listRoulettes() {
		List<Roulette> list = service.findAll();
		return new ResponseEntity<List<Roulette>>(list, HttpStatus.OK);
	}

	@GetMapping("/rouletteOpening/{idRoulette}")
	public ResponseEntity<String> rouletteOpening(@PathVariable Integer idRoulette) {
		String message = service.rouletteOpening(idRoulette);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping("/saveRoulette")
	public ResponseEntity<Integer> saveRoulette(@RequestBody Roulette obj) {
		Integer idRoulette= service.save(obj);
		return new ResponseEntity<Integer>(idRoulette, HttpStatus.CREATED);
	}
	
	@GetMapping("/rouletteBet")
	public ResponseEntity<String> rouletteBet(
			@RequestParam(value="idRoulette")int idRoulette,
			@RequestParam(value="idUser")int idUser,
			@RequestParam(value="Number")int number,
			@RequestParam(value="money")int money
			) {
		String message = service.rouletteBet(idRoulette, idUser, number, money);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping("/closeRoulette/{idRoulette}")
	public ResponseEntity<List<Bet>> closeRoulette(@PathVariable int idRoulette) {
		List<Bet> list = service.closeRoulette(idRoulette);
		return new ResponseEntity<List<Bet>>(list, HttpStatus.OK);
	}
}
