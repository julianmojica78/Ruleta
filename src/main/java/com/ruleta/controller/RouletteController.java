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
import org.springframework.web.bind.annotation.RestController;

import com.ruleta.interfaces.IRouletteService;
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
	public ResponseEntity<Integer> guardar(@RequestBody Roulette obj) {
		Integer idRoulette= service.save(obj);
		return new ResponseEntity<Integer>(idRoulette, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteRoulette/{idRoulette}")
	public ResponseEntity<Object> eliminar(@PathVariable int idRoulette) {
		service.delete(idRoulette);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
