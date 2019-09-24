package com.revature.serf;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.revature.webservice.PokemonWebService;

public class RequestHelper {

	public static void process(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		
		String uri = request.getRequestURI();
		
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.addHeader("Vary", "Origin");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Max-Age", "1728000");
        response.addHeader("Produces", "application/json");
		
        if("OPTIONS".equals(request.getMethod())) {
        	return;
        }
        
		switch(uri) {
		
		case "/PokeApp/getPokemon.do": {
			PokemonWebService.getPokemon(request, response);
			break;
		} case "/PokeApp/addPokemon.do": {
			PokemonWebService.addPokemon(request, response);
			break;
		} case "/PokeApp/getAllPokemon.do" : {
			PokemonWebService.getAllPokemon(request, response);
			break;
		} default:{
			try {
				response.sendError(404);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}
		
	}
	
}
