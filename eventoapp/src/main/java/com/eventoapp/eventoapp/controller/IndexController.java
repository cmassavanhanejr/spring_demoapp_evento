package com.eventoapp.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.repository.EventoRepository;

@Controller
public class IndexController {

	@Autowired
	private EventoRepository er;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv =new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("evento", eventos);
		return mv;
	}
}
