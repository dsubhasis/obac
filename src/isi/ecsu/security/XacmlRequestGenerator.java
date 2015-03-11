/**
 * 
 */
package isi.ecsu.security;

import isi.ecsu.Util.CommonConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.Indenter;
import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ParsingException;
import com.sun.xacml.PolicyTreeElement;
import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.attr.RFC822NameAttribute;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Subject;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.FilePolicyModule;
// TODO: Auto-generated Javadoc

/**
 * The Class XacmlRequestGenerator.
 *
 * @author subhasis
 */
public class XacmlRequestGenerator {
	
	/** The slf4j logger. */
	private Logger slf4jLogger = LoggerFactory.getLogger(XacmlRequestGenerator.class);
	
	/**
	 * Gets the permission value.
	 *
	 * @param userId the user id
	 * @param objectId the object id
	 * @param group the group
	 * @param owner the owner
	 * @return the permission value
	 * @throws FileNotFoundException the file not found exception
	 * @throws ParsingException the parsing exception
	 * @throws URISyntaxException the URI syntax exception
	 */
	public ResponseCtx getPermissionValue(final String userId, final String objectId, final String group,final String owner) throws FileNotFoundException, ParsingException, URISyntaxException
	{
        
        final FilePolicyModule filePolicyModule = new FilePolicyModule();
        final File folder = new File(CommonConstant.XACML_POLICY_DIR);
        int fileCount = 0;
        for(final File fileEntry : folder.listFiles())
        {
        	if(fileEntry.isFile()) {
        	String fName = fileEntry.getAbsolutePath();	
        	filePolicyModule.addPolicy(fileEntry.getAbsolutePath());
        	fileCount++;
        	}
        }
		this.slf4jLogger.info("Total Policy Updataed {}\n", fileCount);
		final PolicyFinder policyFinder = new PolicyFinder();
		final Set<FilePolicyModule> policyModules = new HashSet<FilePolicyModule>();
		policyModules.add(filePolicyModule);
		policyFinder.setModules(policyModules);
		final PDP pdp = new PDP(new PDPConfig(null, policyFinder, null));
		final URI subjectId = new URI("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
		final RFC822NameAttribute value = new RFC822NameAttribute(userId);
		final URI groupId = new URI(group);
		final StringAttribute stringAttribValue = new StringAttribute(owner);
		final URI resource = new URI(objectId);
		final URI actionId = new URI("urn:oasis:names:tc:xacml:1.0:action:action-id");
		final RequestCtx requestUser = createRequest(subjectId, value, groupId, stringAttribValue, resource, actionId);
		requestUser.encode(System.out, new Indenter());
		final ResponseCtx response = pdp.evaluate(requestUser);
	
		return response;
	}
	
	/**
	 * Creates the request.
	 *
	 * @param subjectId the subject id
	 * @param value the value
	 * @param groupId the group id
	 * @param stringAttribValue the string attrib value
	 * @param resource the resource
	 * @param actionId the action id
	 * @return the request ctx
	 * @throws URISyntaxException the URI syntax exception
	 */
	public RequestCtx createRequest(final URI subjectId, final RFC822NameAttribute value, final URI groupId, final StringAttribute stringAttribValue, final URI resource, final  URI actionId ) throws URISyntaxException
	{
		final RequestCtx request =
				new RequestCtx(
						setupSubjects(subjectId, value, groupId, stringAttribValue),
						setupResource(resource),
						setupAction(actionId),
						new HashSet<Object>());
		return request;
	}

	/**
	 * Setup resource.
	 *
	 * @param name the name
	 * @return the sets the
	 * @throws URISyntaxException the URI syntax exception
	 */
	public static Set<Attribute> setupResource(URI name) throws URISyntaxException {
		final HashSet<Attribute> resource = new HashSet<Attribute>();
		final AnyURIAttribute value =
				new AnyURIAttribute(name);
		resource.add(
				new Attribute(
						new URI(EvaluationCtx.RESOURCE_ID),
						null,
						null,
						value));

		return resource;
	}
	
	/**
	 * Setup action.
	 *
	 * @param actionId the action id
	 * @return the sets the
	 * @throws URISyntaxException the URI syntax exception
	 */
	public static Set<Attribute> setupAction(final URI actionId) throws URISyntaxException {
		final HashSet<Attribute> action = new HashSet<Attribute>();
		action.add(
				new Attribute(actionId, null, null, new StringAttribute("open")));
		return action;
	}
	/*
	 * 
	 */
	/**
	 * Setup subjects.
	 *
	 * @param subjectId the subject id
	 * @param value the value
	 * @param groupId the group id
	 * @param stringAttribValue the string attrib value
	 * @return the sets the
	 * @throws URISyntaxException the URI syntax exception
	 */
	public static Set<Subject> setupSubjects(final URI subjectId, final RFC822NameAttribute value, final URI groupId, final StringAttribute stringAttribValue  ) throws URISyntaxException {
		final HashSet<Attribute> attributes = new HashSet<Attribute>();
		attributes.add(new Attribute(subjectId, null, null, value));
		attributes.add(new Attribute(groupId, null, null, stringAttribValue));
		final HashSet<Subject> subjects = new HashSet<Subject>();
		subjects.add(new Subject(attributes));
		return subjects;
	}
	/*
	 * 
	 */
	/**
	 * Instantiates a new xacml request generator.
	 */
	public XacmlRequestGenerator() {
	}



}
