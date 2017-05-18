package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private Multigraph<Author,PaperEdge> grafo ;
	private Map<Integer,Author> autori ;
	private Map<Integer,Paper> articoli ;
	
	public Model(){
		this.grafo = new Multigraph<>(PaperEdge.class);
		this.autori = new HashMap<>();
		this.articoli = new HashMap<>();
		PortoDAO dao = new PortoDAO();
		dao.caricaDati(autori,articoli);
		Graphs.addAllVertices(grafo, autori.values());
		for(Paper p : this.articoli.values()){
			List<Author> list = p.getAuthors();
			for(int i=0 ; i<list.size()-1 ; i++)
				for(int j=i+1 ; j<list.size() ; j++)
					if(!list.get(i).equals(list.get(j))){
						PaperEdge e = new PaperEdge(p);
						grafo.addEdge(list.get(i), list.get(j),e);
					}
		}

		
	}

	public String getCoAutori(Author value) {
		String s = "" ;
		for(Author a : Graphs.neighborListOf(grafo, value))
			s+=a.toString();
		if(s.compareTo("")==0)
			return "L'autore scelto non ha co-autori" ;
		return s;
			
	}

	public Collection<Author> getAutori() {
		List<Author> temp = new LinkedList<Author>(autori.values());
		Collections.sort(temp);
		return temp;
	}

	public String getSequenza(Author primo, Object secondo) {
		if(Graphs.neighborListOf(grafo, primo).contains(secondo))
			return "L'autore scelto è già un co-autore";
		else {
			DijkstraShortestPath<Author,PaperEdge> dsp = new DijkstraShortestPath(grafo,primo,secondo) ;
			String s = "";
			for(PaperEdge p : dsp.getPathEdgeList())
				s+=p.toString();
			return s;
		}
		
	}
	
	

}
