package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.clique.DegeneracyBronKerboschCliqueFinder;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;
import it.polito.tdp.borders.db.Confinanti;

public class Model {

	private Graph<Country, DefaultEdge> grafo;
	private Map<Integer, Country> idMap;
	private BordersDAO dao;
	private List<Country> nazioni;
	private Map<Country, Country> predecessore;

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
		Collections.sort(nazioni);

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
	
	//LIBRERIA J-GRAPHT
	
	public List<Country> statiRaggiungibili(Country partenza) {
		
		this.predecessore = new HashMap<>();
		this.predecessore.put(partenza, null);
		
		BreadthFirstIterator<Country, DefaultEdge> bfi = new BreadthFirstIterator<>(this.grafo, partenza);
		
		DepthFirstIterator<Country, DefaultEdge> dfi = new DepthFirstIterator<>(this.grafo, partenza);
		
		List<Country> result = new ArrayList<>();
		
	/*	dfi.addTraversalListener(new TraversalListener<Country, DefaultEdge>(){

			@Override
			public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
			}

			@Override
			public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
			}

			@Override
			public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {			
				DefaultEdge arco = e.getEdge();
				Country c1 = grafo.getEdgeSource(arco);
				Country c2 = grafo.getEdgeTarget(arco);
				if(predecessore.containsKey(c2) && !predecessore.containsKey(c1)) {
					predecessore.put(c1, c2);
				}else if(predecessore.containsKey(c1) && !predecessore.containsKey(c2)) {
					predecessore.put(c2, c1);
				}
			}

			@Override
			public void vertexTraversed(VertexTraversalEvent<Country> e) {
			}

			@Override
			public void vertexFinished(VertexTraversalEvent<Country> e) {	
			}
			
		}); */
		
		while(dfi.hasNext()) {
			Country c = dfi.next();
			result.add(c);
		}
		
		return result;
	}
	
	//VERSIONE RICORSIVA
	
	public List<Country> statiRaggiungibili2(Country partenza){
		
		List<Country> result = new ArrayList<>();
		ricorsione(result, partenza);
		return result;
	}

	private void ricorsione(List<Country> result, Country c) {
		
		result.add(c);
		
		for(Country iter: Graphs.neighborListOf(this.grafo, c)) {
			if(!result.contains(iter)) {
				ricorsione(result, iter);
			}
		}
	}

}
