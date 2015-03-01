/*
 * 
 */
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.ResultSet;

import virtuoso.jena.driver.VirtGraph;
import isi.ecsu.Util.commonUtil;
import isi.ecsu.view.struct.impl.View;
import isi.ecsu.view.struct.impl.ViewGenerationModule;




public class VirtuosoSPARQLExample1 {

	/**
	 * Executes a SPARQL query against a virtuoso url and prints results.
	 */
	public static void main(String[] args) {
		String virtUrl = "jdbc:virtuoso://localhost:1111";
		VirtGraph vg = commonUtil.virtConnect("dba", "dba", virtUrl);
		String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> SELECT * FROM <http://ecsu.org> WHERE {  ?p skos:prefLabel ?q .}";
		View vw = new ViewGenerationModule();
		String roleName = null, rootObject = "DL-Concept40";
		OntModel lmodel = null;
		try {
			OntModel om = vw.roleView(lmodel, roleName, rootObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}