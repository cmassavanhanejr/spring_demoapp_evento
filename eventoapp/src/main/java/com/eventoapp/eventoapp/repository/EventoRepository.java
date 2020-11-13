/**
 * 
 */
package com.eventoapp.eventoapp.repository;


import org.springframework.data.repository.CrudRepository;

import com.eventoapp.eventoapp.model.Evento;

/**
 * @author CAMJr
 *
 */
public interface EventoRepository extends CrudRepository<Evento, Long> {
	Evento findByCodigo(long codigo);
	
	
}
