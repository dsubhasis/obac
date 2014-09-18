package isi.ecsu.dataSet;


import java.io.*;
import java.util.ArrayList;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.util.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class RandomOntology {

	OntModel randOntology;

	int[][] randMatrix;
	String uri;
	int numberOfNodes;

	void GenerateRandomMatrix(int n)
	{
		randMatrix = new int[n][n];

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < i; j++)
			{
				double r = Math.random();

				if (r > 0.95) {
					randMatrix[i][j] = 1;
				} else {
					randMatrix[i][j] = 0;
			}		

				//System.out.print(randMatrix[i][j] + "\t");
			}
			//System.out.println("-");
		}			
	}

	public OntModel GenerateOntology()
	{
		GenerateRandomMatrix(numberOfNodes);
		return GenerateOntology(numberOfNodes);

	}

	public OntModel GenerateOntology(int n)
	{
		randOntology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		uri = "http://www.iwi-iuk.org/material/RDF/Schema/Class/scf#DL-Concept"; // take it as an argument
		OntClass randClass = null;

		randOntology.createProperty("http://ontology.project/#hasContributedto");
		Property hCt = randOntology.getProperty("http://ontology.project/#hasContributedto");

		for (int i = 0; i < n; i++)
		{
			randClass = randOntology.createClass( uri + i);			
		}

		for (int i = 0; i < n; i++)
		{
			randClass = randOntology.getOntClass( uri + i);
			for (int j = 0; j < i; j++)
			{				
				if (randMatrix[i][j] == 1)
				{
					randClass.addSubClass(randOntology.getOntClass(uri + j));
					randClass.addProperty(hCt, randOntology.getOntClass(uri + j));
				}

			}
		}

		return randOntology;
	}

	RandomOntology(int n)
	{
		numberOfNodes = n;
		randOntology = null;
	}

	void GeneratePermissions()
	{
		if (randOntology != null)
		{
			for (int i = 0; i < numberOfNodes; i++)
			{
				String perm;

				double r = Math.random();
				if (r < 0.3)
					perm = "default";
				else if (r < 0.6)
					perm = "positive";
				else
					perm = "negative";


				//System.out.println( uri + i + "," + perm);
			}
		}

		return;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomOntology r = new RandomOntology(10000);
		OntModel o = r.GenerateOntology();
		//o.write(System.out);

		File ontologyOutput = new File("/Users/subhasis/Documents/workspace/trunk/trunk/src/isi/ecsu/dataSet/ontology_1L.owl");

		if (!ontologyOutput.exists())
		{
			try {
				ontologyOutput.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileOutputStream fops = null;

		try {
			fops = new FileOutputStream(ontologyOutput);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		o.write(fops);

		//r.GeneratePermissions();
	}

}
