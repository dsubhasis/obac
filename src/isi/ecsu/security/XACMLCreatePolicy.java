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

	public Target createRuleTarget(String actionName)
			throws URISyntaxException, UnknownIdentifierException,
			FunctionTypeException {
		List actions = new ArrayList();
		// create the Action section
		List action = new ArrayList();
		String actionMatchId = "urn:oasis:names:tc:xacml:1.0:function:string-equal";
		final URI actionDesignatorType = new URI(
				"http://www.w3.org/2001/XMLSchema#string");
		final URI actionDesignatorId = new URI(
				"urn:oasis:names:tc:xacml:1.0:action:action-id");
		final AttributeDesignator actionDesignator = new AttributeDesignator(
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
		FunctionFactory factory = FunctionFactory.getConditionInstance();
		Function conditionFunction = null;
		try {
			conditionFunction = factory
					.createFunction("urn:oasis:names:tc:xacml:1.0:function:"
							+ "string-equal");
		} catch (Exception e) {
			return null;
		}
		List applyArgs = new ArrayList();
		factory = FunctionFactory.getGeneralInstance();
		Function applyFunction = null;
		try {
			applyFunction = factory
					.createFunction("urn:oasis:names:tc:xacml:1.0:function:"
							+ "string-one-and-only");
		} catch (Exception e) {
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
		conditionArgs.add(apply);
		StringAttribute value = new StringAttribute("developers");
		conditionArgs.add(value);
		return new Apply(conditionFunction, conditionArgs, true);
	}

	public Rule createRule(String actionValue) throws URISyntaxException,
			UnknownIdentifierException, FunctionTypeException {
		URI ruleId = new URI("CommitRule");
		int effect = Result.DECISION_PERMIT;
		Target target = createRuleTarget(actionValue);
		Apply condition = createRuleCondition();
		return new Rule(ruleId, effect, null, target, condition);
	}

	public Target createPolicyTarget(String subjectName, String objectName)
			throws URISyntaxException, UnknownIdentifierException,
			FunctionTypeException {
		List subjects = new ArrayList();
		List resources = new ArrayList();

		// create the Subject section
		List subject = new ArrayList();

		String subjectMatchId = "urn:oasis:names:tc:xacml:1.0:function:rfc822Name-match";

		URI subjectDesignatorType = new URI(
				"urn:oasis:names:tc:xacml:1.0:data-type:rfc822Name");
		URI subjectDesignatorId = new URI(
				"urn:oasis:names:tc:xacml:1.0:subject:subject-id");
		AttributeDesignator subjectDesignator = new AttributeDesignator(
				AttributeDesignator.SUBJECT_TARGET, subjectDesignatorType,
				subjectDesignatorId, false);

		
		URI svalue = new URI(subjectName);
		String spath = svalue.getPath();
		String host= svalue.getHost();
		String delims = "#";
		String[] nodeName = subjectName.split(delims);
		int n = nodeName.length;
		
		String sp = spath.substring(spath.lastIndexOf("/"));
		
		sp = spath.replaceAll("/", ".");
		//sp = sp.replaceFirst(".", "@");
		
		final String finalSubject = nodeName[n - 1]+"@"+host+sp;
		
		
		StringAttribute subjectValue = new StringAttribute(finalSubject);

		subject.add(createTargetMatch(TargetMatch.SUBJECT, subjectMatchId,
				subjectDesignator, subjectValue));
		List resource = new ArrayList();
		String resourceMatchId = "urn:oasis:names:tc:xacml:1.0:function:anyURI-equal";
		URI resourceDesignatorType = new URI(
				"http://www.w3.org/2001/XMLSchema#anyURI");
		URI resourceDesignatorId = new URI(
				"urn:oasis:names:tc:xacml:1.0:resource:resource-id");
		AttributeDesignator resourceDesignator = new AttributeDesignator(
				AttributeDesignator.RESOURCE_TARGET, resourceDesignatorType,
				resourceDesignatorId, false);
		AnyURIAttribute resourceValue = new AnyURIAttribute(new URI(objectName));
		resource.add(createTargetMatch(TargetMatch.RESOURCE, resourceMatchId,
				resourceDesignator, resourceValue));
		subjects.add(subject);
		resources.add(resource);
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
	public final URI getPolicyId() {
		return policyId;
	}
	public final void setPolicyId(URI policyId) {
		this.policyId = policyId;
	}
	public final URI getCombiningAlgId() {
		return combiningAlgId;
	}
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
	public String XACMLPolicyUpdate(URI policyId, URI combiningAlgId,
			String actionValue, String subjectName, String objectName,
			String policyStoreFile, int resultDecsiion) throws UnknownIdentifierException,
			URISyntaxException, FunctionTypeException, FileNotFoundException {

		setPolicyId(policyId);

		setCommitRule(commitRule);
		// URI combiningAlgId = new URI(OrderedPermitOverridesRuleAlg.algId);
		CombiningAlgFactory factory = CombiningAlgFactory.getInstance();
		RuleCombiningAlgorithm combiningAlg = (RuleCombiningAlgorithm) (factory
				.createAlgorithm(combiningAlgId));
		// add a description for the policy
		String description = "This policy applies to any accounts at "
				+ policyId
				+ "accessing server.example.com. The one Rule applies to the "
				+ "specific action of doing a CVS commit, but other Rules could "
				+ "be defined that handled other actions. In this case, only "
				+ "certain groups of people are allowed to commit. There is a "
				+ "final fall-through rule that always returns Deny.";
		setPolicyTarget(createPolicyTarget(subjectName, objectName));
		setCommitRule(createRule(actionValue));
		setDefaultRule(new Rule(new URI("FinalRule"), resultDecsiion,
				null, null, null));
		ruleList = new ArrayList();
		ruleList.add(commitRule);
		ruleList.add(defaultRule);
		setPolicy(new Policy(policyId, combiningAlg, description, policyTarget,
				ruleList));

		slf4jLogger.info("Policy Created for " + objectName + "and"
				+ subjectName);
		
		OutputStream ot = new FileOutputStream(new File(policyStoreFile));
		policy.encode(ot);
		slf4jLogger.info("Policy written at store " + policyStoreFile
				+ " for @ " + objectName + "and" + subjectName);
		return policyStoreFile;

	}

	/**
	 * 
	 */
	public XACMLCreatePolicy() {
	}

}
