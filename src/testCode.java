/*
 * 
 */
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import com.sun.xacml.Target;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.combine.OrderedPermitOverridesRuleAlg;
import com.sun.xacml.cond.FunctionTypeException;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.mysqlJava;
import isi.ecsu.security.XACMLCreatePolicy;
import isi.ecsu.security.createRandomPolicy;

/**
 * 
 */

/**
 * @author subhasis
 * 
 */
public class testCode {

	/**
	 * @param args
	 * @throws FunctionTypeException
	 * @throws URISyntaxException
	 * @throws UnknownIdentifierException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws UnknownIdentifierException,
			URISyntaxException, FunctionTypeException, FileNotFoundException {
		createRandomPolicy cr = new createRandomPolicy();
		String objectRoot = "DL-Concept19";
		String objectGraph = CommonConstant.ObjectOntologyStorage;
		String objectUri = CommonConstant.ObjectCommonURI;
		String objectPrefix = CommonConstant.ObjectPrefix;
		String objectOntologyRelation = CommonConstant.ObjectRelation00;
		String subjectRoot = "Business";
		String subjectGraph = CommonConstant.SubjectOntologyStorage;
		String subjectUri = CommonConstant.SubjectCommonURI;
		String subjectPrefix = CommonConstant.SubjectPrefix;
		String subjectOntologyRelation = CommonConstant.SubjectRelation00;
		cr.XacmlPolicyGen(objectRoot, objectGraph, objectUri, objectPrefix,
				objectOntologyRelation, subjectRoot, subjectGraph, subjectUri,
				subjectPrefix, subjectOntologyRelation);
	}
}
