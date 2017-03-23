package es.daw.dirando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.dirando.model.Publicidad;

import es.daw.dirando.repository.PublicidadRepository;

@Service
public class PublicServices {
	
	@Autowired
	private PublicidadRepository publicityRepository;
	
		public Iterable<Publicidad> findAllPublicity(){
			return publicityRepository.findAll();
		}
}