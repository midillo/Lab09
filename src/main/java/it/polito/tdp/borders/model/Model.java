package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;
import it.polito.tdp.borders.db.Confinanti;

public class Model {

	private Graph<Country, DefaultEdge> grafo;
	private Map<Integer, Country> idMap;
	private BordersDAO dao;
	private List<Country> nazioni;

	public Model() {
		idMap = new HashMap<>();
		dao = new BordersDAO();
	}

	public void creaGrafo(int annoUtente) {
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);

		dao.getCountry(idMap, annoUtente);
		
		Graphs.addAllVertices(this.grafo, idMap.values());

		List<Confinanti> confini = dao.getArchi();

		for(Confinanti c: confini) {
			if(idMap.containsKey(c.getCodiceNazione1()) && idMap.containsKey(c.getCodiceNazione2())) {
				grafo.addEdge(idMap.get(c.getCodiceNazione1()), idMap.get(c.getCodiceNazione2()));
			}
		}

		nazioni = new ArrayList<>(this.grafo.vertexSet());

	}

	public int numVertici() {
		return this.grafo.vertexSet().size();
	}

	public int numArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Country> getVertici(){
		if(nazioni == null) {
			return new ArrayList<Country>();
		}
		return nazioni;
	}

	public int getComponentiConnesse() {
		if(grafo == null) {
			throw new RuntimeException("Grafo non esistente!");
		}
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<>(grafo);
		return ci.connectedSets().size();
	}


	public Map<Country, Integer> getNazioni(){
		if(grafo == null) {
			throw new RuntimeException("Grafo non esistente!");
		}
		Map<Country, Integer> mappa = new HashMap<>();
		for(Country nazione: grafo.vertexSet()) {
			mappa.put(nazione, grafo.degreeOf(nazione));
		}

		return mappa;
	}
}
