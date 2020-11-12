package com.eventoapp.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	
	@RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@PostMapping("/cadastrarEvento")
	public String form(Evento evento) {
		er.save(evento);
		return "redirect:/cadastrarEvento";
	}
	
	
}
