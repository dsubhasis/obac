/**
 * 
 */
package isi.ecsu.view.struct;

import java.util.Date;
import java.util.List;



/**
 * @author subhasis
 *
 */
public class Organization extends Subject{
	/**
	 * @param name
	 * @param roleName
	 */
	public Organization(String name, List roleName) {
		super(name, roleName);
		// TODO Auto-generated constructor stub
	}
	private String keyFile;
	private String organiztion;
	private String contactPeron; 
	private String webServiceType;
	private Date dateOfexp;
	private String informations;
	/**
	 * @return the keyFile
	 */
	public final String getKeyFile() {
		return keyFile;
	}
	/**
	 * @param keyFile the keyFile to set
	 */
	public final void setKeyFile(String keyFile) {
		this.keyFile = keyFile;
	}
	/**
	 * @return the organiztion
	 */
	public final String getOrganiztion() {
		return organiztion;
	}
	/**
	 * @param organiztion the organiztion to set
	 */
	public final void setOrganiztion(String organiztion) {
		this.organiztion = organiztion;
	}
	/**
	 * @return the contactPeron
	 */
	public final String getContactPeron() {
		return contactPeron;
	}
	/**
	 * @param contactPeron the contactPeron to set
	 */
	public final void setContactPeron(String contactPeron) {
		this.contactPeron = contactPeron;
	}
	/**
	 * @return the webServiceType
	 */
	public final String getWebServiceType() {
		return webServiceType;
	}
	/**
	 * @param webServiceType the webServiceType to set
	 */
	public final void setWebServiceType(String webServiceType) {
		this.webServiceType = webServiceType;
	}
	/**
	 * @return the dateOfexp
	 */
	public final Date getDateOfexp() {
		return dateOfexp;
	}
	/**
	 * @param dateOfexp the dateOfexp to set
	 */
	public final void setDateOfexp(Date dateOfexp) {
		this.dateOfexp = dateOfexp;
	}
	/**
	 * @return the informations
	 */
	public final String getInformations() {
		return informations;
	}
	/**
	 * @param informations the informations to set
	 */
	public final void setInformations(String informations) {
		this.informations = informations;
	}

}
