package org.xaloon.wicket.demo.extended;

import org.xaloon.wicket.plugin.user.RegistrationModel;

/**
 * @author vytautas r.
 *
 */
public class ExtendedRegistrationModel extends RegistrationModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The phone number */
	private String phoneNumber;

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
