/**
 * 
 */
package isi.ecsu.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xacml.Indenter;
import com.sun.xacml.Policy;
import com.sun.xacml.Rule;
import com.sun.xacml.Target;
import com.sun.xacml.TargetMatch;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.combine.CombiningAlgFactory;
import com.sun.xacml.combine.RuleCombiningAlgorithm;
import com.sun.xacml.cond.Apply;
import com.sun.xacml.cond.Function;
import com.sun.xacml.cond.FunctionFactory;
import com.sun.xacml.cond.FunctionTypeException;
import com.sun.xacml.ctx.Result;

/**
 * @author subhasis
 * 
 */
public class XACMLCreatePolicy {

	public TargetMatch createTargetMatch(int type, String functionId,
			AttributeDesignator designator, AttributeValue value)
			throws UnknownIdentifierException, FunctionTypeException {

		FunctionFactory factory = FunctionFactory.getTargetInstance();
		Function function = factory.createFunction(functionId);
		return new TargetMatch(type, function, designator, value);

	}

	

	public Target createRuleTarget(String actionName) throws URISyntaxException,
			UnknownIdentifierException, FunctionTypeException {
		List actions = new ArrayList();
		// create the Action section
		List action = new ArrayList();
		String actionMatchId = "urn:oasis:names:tc:xacml:1.0:function:string-equal";
		URI actionDesignatorType = new URI(
				"http://www.w3.org/2001/XMLSchema#string");
		URI actionDesignatorId = new URI(
				"urn:oasis:names:tc:xacml:1.0:action:action-id");
		AttributeDesignator actionDesignator = new AttributeDesignator(
				AttributeDesignator.ACTION_TARGET, actionDesignatorType,
				actionDesignatorId, false);
		StringAttribute actionValue = new StringAttribute(actionName);
		action.add(createTargetMatch(TargetMatch.ACTION, actionMatchId,
				actionDesignator, actionValue));
		// put the Action section in the Actions list
		actions.add(action);
		// create & return the new Target
		return new Target(null, null, actions);
	}

	public Apply createRuleCondition() throws URISyntaxException {
		List conditionArgs = new ArrayList();
		// get the function that the condition uses
		FunctionFactory factory = FunctionFactory.getConditionInstance();
		Function conditionFunction = null;
		try {
			conditionFunction = factory
					.createFunction("urn:oasis:names:tc:xacml:1.0:function:"
							+ "string-equal");
		} catch (Exception e) {
			// see comment in createTargetMatch()
			return null;
		}
		// now create the apply section that gets the designator value
		List applyArgs = new ArrayList();
		factory = FunctionFactory.getGeneralInstance();
		Function applyFunction = null;
		try {
			applyFunction = factory
					.createFunction("urn:oasis:names:tc:xacml:1.0:function:"
							+ "string-one-and-only");
		} catch (Exception e) {
			// see comment in createTargetMatch()
			return null;
		}
		URI designatorType = new URI("http://www.w3.org/2001/XMLSchema#string");
		URI designatorId = new URI("group");
		URI designatorIssuer = new URI("admin@users.example.com");
		AttributeDesignator designator = new AttributeDesignator(
				AttributeDesignator.SUBJECT_TARGET, designatorType,
				designatorId, false, designatorIssuer);
		applyArgs.add(designator);
		Apply apply = new Apply(applyFunction, applyArgs, false);
		// add the new apply element to the list of inputs to the condition
		conditionArgs.add(apply);
		// create an AttributeValue and add it to the input list
		StringAttribute value = new StringAttribute("developers");
		conditionArgs.add(value);
		// finally, create & return our Condition
		return new Apply(conditionFunction, conditionArgs, true);
	}

	public Rule createRule(String actionValue) throws URISyntaxException,
			UnknownIdentifierException, FunctionTypeException {
		// define the identifier for the rule
		URI ruleId = new URI("CommitRule");
		// define the effect for the Rule
		int effect = Result.DECISION_PERMIT;
		// get the Target for the rule
		Target target = createRuleTarget(actionValue);
		// get the Condition for the rule
		Apply condition = createRuleCondition();
		return new Rule(ruleId, effect, null, target, condition);
	}

	/**
	 * Command-line routine that bundles together all the information needed to
	 * create a Policy and then encodes the Policy, printing to standard out.
	 * 
	 * @throws FunctionTypeException
	 * @throws UnknownIdentifierException
	 */

	public Target createPolicyTarget(String subjectName, String objectName) throws URISyntaxException,
			UnknownIdentifierException, FunctionTypeException {
		 List subjects = new ArrayList();
	        List resources = new ArrayList();

	        // create the Subject section
	        List subject = new ArrayList();
		
		String subjectMatchId =
	            "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-match";

	        URI subjectDesignatorType =
	            new URI("urn:oasis:names:tc:xacml:1.0:data-type:rfc822Name");
	        URI subjectDesignatorId =
	            new URI("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
	        AttributeDesignator subjectDesignator =
	            new AttributeDesignator(AttributeDesignator.SUBJECT_TARGET,
	                                    subjectDesignatorType,
	                                    subjectDesignatorId, false);

	        StringAttribute subjectValue =
	            new StringAttribute(subjectName);
	        
	        subject.add(createTargetMatch(TargetMatch.SUBJECT, subjectMatchId,
	                                      subjectDesignator, subjectValue));

	        // create the Resource section
	        List resource = new ArrayList();

	        String resourceMatchId =
	            "urn:oasis:names:tc:xacml:1.0:function:anyURI-equal";

	        URI resourceDesignatorType =
	            new URI("http://www.w3.org/2001/XMLSchema#anyURI");
	        URI resourceDesignatorId =
	            new URI("urn:oasis:names:tc:xacml:1.0:resource:resource-id");
	        AttributeDesignator resourceDesignator =
	            new AttributeDesignator(AttributeDesignator.RESOURCE_TARGET,
	                                    resourceDesignatorType,
	                                    resourceDesignatorId, false);

	        AnyURIAttribute resourceValue =
	            new AnyURIAttribute(new URI(objectName));

	        resource.add(createTargetMatch(TargetMatch.RESOURCE, resourceMatchId,
	                                       resourceDesignator, resourceValue));

	        // put the Subject and Resource sections into their lists
	        subjects.add(subject);
	        resources.add(resource);

	        // create & return the new Target
	        return new Target(subjects, resources, null);
	}

	private Logger slf4jLogger = LoggerFactory
			.getLogger(XACMLCreatePolicy.class);
	private URI policyId;
	private URI combiningAlgId;
	private Target policyTarget;
	private Rule commitRule;
	private Rule defaultRule;
	private List ruleList;
	private Policy policy;

	/**
	 * @return the policyId
	 */
	public final URI getPolicyId() {
		return policyId;
	}

	/**
	 * @param policyId
	 *            the policyId to set
	 */
	public final void setPolicyId(URI policyId) {
		this.policyId = policyId;
	}

	/**
	 * @return the combiningAlgId
	 */
	public final URI getCombiningAlgId() {
		return combiningAlgId;
	}

	/**
	 * @param combiningAlgId
	 *            the combiningAlgId to set
	 */
	public final void setCombiningAlgId(URI combiningAlgId) {
		this.combiningAlgId = combiningAlgId;
	}

	/**
	 * @return the policyTarget
	 */
	public final Target getPolicyTarget() {
		return policyTarget;
	}

	/**
	 * @param policyTarget
	 *            the policyTarget to set
	 */
	public final void setPolicyTarget(Target policyTarget) {
		this.policyTarget = policyTarget;
	}

	/**
	 * @return the commitRule
	 */
	public final Rule getCommitRule() {
		return commitRule;
	}

	/**
	 * @param commitRule
	 *            the commitRule to set
	 */
	public final void setCommitRule(Rule commitRule) {
		this.commitRule = commitRule;
	}

	/**
	 * @return the defaultRule
	 */
	public final Rule getDefaultRule() {
		return defaultRule;
	}

	/**
	 * @param defaultRule
	 *            the defaultRule to set
	 */
	public final void setDefaultRule(Rule defaultRule) {
		this.defaultRule = defaultRule;
	}

	/**
	 * @return the ruleList
	 */
	public final List getRuleList() {
		return ruleList;
	}

	/**
	 * @param ruleList
	 *            the ruleList to set
	 */
	public final void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}

	/**
	 * @return the policy
	 */
	public final Policy getPolicy() {
		return policy;
	}

	/**
	 * @param policy
	 *            the policy to set
	 */
	public final void setPolicy(Policy policy) {
		this.policy = policy;
	}

	/**
	 * @param policyId
	 * @param combiningAlgId
	 * @param policyTarget
	 * @param commitRule
	 * @param defaultRule
	 * @param ruleList
	 * @param policy
	 * @throws UnknownIdentifierException
	 * @throws FunctionTypeException
	 * @throws URISyntaxException
	 * @throws FileNotFoundException 
	 */
	public XACMLCreatePolicy(URI policyId, URI combiningAlgId, String actionValue, String objectName, String subjectName, String policyStoreFile
			) throws UnknownIdentifierException,
			URISyntaxException, FunctionTypeException, FileNotFoundException {

		setPolicyId(policyId);

		setCommitRule(commitRule);
		// URI combiningAlgId = new URI(OrderedPermitOverridesRuleAlg.algId);
		CombiningAlgFactory factory = CombiningAlgFactory.getInstance();
		RuleCombiningAlgorithm combiningAlg = (RuleCombiningAlgorithm) (factory
				.createAlgorithm(combiningAlgId));
		// add a description for the policy
		String description = "This policy applies to any accounts at " + objectName 
				+ "accessing server.example.com. The one Rule applies to the "
				+ "specific action of doing a CVS commit, but other Rules could "
				+ "be defined that handled other actions. In this case, only "
				+ "certain groups of people are allowed to commit. There is a "
				+ "final fall-through rule that always returns Deny.";
		// create the target for the policy

		setPolicyTarget(createPolicyTarget(subjectName, objectName));
		setCommitRule(createRule(actionValue));
		setDefaultRule(new Rule(new URI("FinalRule"), Result.DECISION_DENY,
				null, null, null));
		// Rule defaultRule = new Rule(new URI("FinalRule"),
		// Result.DECISION_DENY,
		// null, null, null);
		// create a list for the rules and add our rules in order
		ruleList = new ArrayList();
		ruleList.add(commitRule);
		ruleList.add(defaultRule);
		// create the policy
		setPolicy(new Policy(policyId, combiningAlg, description, policyTarget,
				ruleList));

		// finally, encode the policy and print it to standard out
		//policy.encode(System.out, new Indenter());
	slf4jLogger.info("Policy Created for "+objectName+ "and" +subjectName);
		
		OutputStream ot = new FileOutputStream(new File(policyStoreFile));
		policy.encode(ot);
		slf4jLogger.info("Policy written at store "+policyStoreFile+ " for @ " +objectName+ "and" +subjectName);
		
	}

}
