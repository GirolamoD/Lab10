package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private SimpleGraph<Author,DefaultEdge> grafo ;
	private Map<Integer,Author> autori ;
	private Map<Integer,Paper> articoli ;
	
	public Model(){
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		this.autori = new HashMap<>();
		this.articoli = new HashMap<>();
		PortoDAO dao = new PortoDAO();
		dao.generaGrafo(grafo,autori,articoli);
		
	}

	public String getCoAutori(Author value) {
		return null;
	}

	public Author getAutori() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
