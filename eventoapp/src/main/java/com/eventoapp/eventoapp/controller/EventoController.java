package com.eventoapp.eventoapp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	@RequestMapping("/detalhesEvento/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		
		Evento evento=er.findByCodigo(codigo);
		ModelAndView mv=new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento",evento);
		
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@RequestMapping("/apagarEvento/{codigo}")
	public String apagarEvento(@PathVariable("codigo") long codigo) {
		Evento evento =er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
	}
	
	@RequestMapping("/apagarConvidado/{rg}")
	public String apagarConvidado(@PathVariable("rg") String rg) {
		Convidado convidado=cr.findByRg(rg);
		cr.delete(convidado);
		
		Evento evento=convidado.getEvento();
		
		String codigo=evento.getCodigo()+"";
		
		
		return "redirect:/detalhesEvento/"+codigo;
	}
	

	
	@PostMapping("/detalhesEvento/{codigo}")
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado,BindingResult result, RedirectAttributes attributes) {
		
		/*if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "verifique os campos");
			return "redirect:/detalhesEvento/{codigo}";
		}*/
		Evento evento=er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "convidado adicionado com sucesso");

		return "redirect:/detalhesEvento/{codigo}";
	}
	
	
	
}
