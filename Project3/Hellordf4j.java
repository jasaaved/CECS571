package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

public class Hellordf4j {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Repository rep = new SailRepository(new MemoryStore());
		rep.initialize();

		File overdoseFile = new File("C:\\Users\\gutie\\Documents\\GitHub\\CECS571\\Project2-Accidents-Python\\complex.rdf");
		File weatherFile = new File("C:\\Users\\gutie\\Documents\\GitHub\\CECS571\\Project2-Connecticut-Weather\\Connecticut_Weather.owl");
		String weatherBaseURI = "http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather";
		String overdoseBaseURI = "https://data.ct.gov/resource/";
		
		try(RepositoryConnection con = rep.getConnection()) {
			//con.add(overdoseFile, overdoseBaseURI, RDFFormat.RDFXML);
			con.add(weatherFile, weatherBaseURI, RDFFormat.RDFXML);
			//String query = "PREFIX : <http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#>"
			//		+ "SELECT ?x ?y "
			//		+ "WHERE { "
			//		+ "?x :prcp ?y . "
			//		+ "FILTER (!isBlank(?y))"
			//		+ "} "
			//		+ "LIMIT 50";
			
			String query = "PREFIX conw: <http://www.semanticweb.org/davidcaplin/ontologies/2020/2/Connecticut-Weather#> "
					+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
					+ "SELECT (avg(?y) AS ?average) "
					+ "WHERE { "
					+ "?x conw:prcp ?y . "
					+ "FILTER ( ?y >= \"0\"^^xsd:float ) "
					+ "} ";
			
			TupleQuery tq = con.prepareTupleQuery(query);
			try (TupleQueryResult result = tq.evaluate()) {
				List<String> bindingNames = result.getBindingNames();
				
				for (String s: bindingNames) {
					System.out.print(s + " | ");
				}
				
				System.out.println();
				
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					
					for (String name: bindingNames) {
						System.out.print(bindingSet.getValue(name) + " | ");
					}
					
					System.out.println();
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
