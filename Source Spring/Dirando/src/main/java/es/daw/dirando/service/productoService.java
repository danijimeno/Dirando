package es.daw.dirando.service;

import org.springframework.stereotype.Service;

@Service
public class productoService {

	public String stockControl(int num){
		if(num <5){
			return "alert";
		}else if(num < 20){
			return "warning";
		}
		return " ";
	}
}
