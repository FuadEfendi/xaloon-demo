package org.xaloon.wicket.demo.extended;

import org.xaloon.core.api.user.model.User;

/**
 * @author vytautas r.
 *
 */
public interface ExtendedUser extends User {
	/**
	 * @return the phoneNumber
	 */
	String getPhoneNumber();

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	void setPhoneNumber(String phoneNumber);
}
