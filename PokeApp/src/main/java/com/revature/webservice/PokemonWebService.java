package com.revature.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Pokemon;
import com.revature.service.PokemonService;

public class PokemonWebService {
	
	public static ObjectMapper om = new ObjectMapper();

	public static void getPokemon(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		Pokemon p = PokemonService.getPokemon(id);
		
		try {
			String pokemonJSON = om.writeValueAsString(p);
			response.getWriter().append(pokemonJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void addPokemon(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		
//		int id = Integer.parseInt(request.getParameter("id"));
//		String name = request.getParameter("name");
//		String type = request.getParameter("type");
//		
//		System.out.println(id + " " + name + " " + type);
//		
//		Pokemon p = new Pokemon(id, name, type);
		Pokemon p = (Pokemon) om.readValue(request.getInputStream(), Pokemon.class);
		System.out.println(p);
		PokemonService.addPokemon(p);
		System.out.println("Addition was successful");
		
		try {
			response.getWriter().append(om.writeValueAsString(p));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void getAllPokemon(HttpServletRequest request, HttpServletResponse response) {
		List<Pokemon> polkamans = PokemonService.getAllPokemon();
		
		try {
			response.getWriter().append(om.writeValueAsString(polkamans));
			return;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
