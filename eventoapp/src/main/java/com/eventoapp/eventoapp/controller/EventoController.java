package com.eventoapp.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.eventoapp.model.Convidado;
import com.eventoapp.eventoapp.model.Evento;
import com.eventoapp.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;
	
	
	@RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@PostMapping("/cadastrarEvento")
	public String form(Evento evento) {
		er.save(evento);
		return "redirect:/eventos";
	}
	
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv =new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("evento", eventos);
		return mv;
	}
	
	
	@RequestMapping("/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		
		Evento evento=er.findByCodigo(codigo);
		ModelAndView mv=new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento",evento);
		return mv;
	}
	
	@PostMapping("/{codigo}")
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado) {
		Evento evento=er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		
		return "redirect:/{codigo}";
	}
	
	
	
}
