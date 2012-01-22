package org.xaloon.wicket.demo.extended;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.xaloon.core.jpa.user.model.JpaUser;

/**
 * @author vytautas r.
 *
 */
@Entity
@Table(name = "XAL_USER_EXTENDED")
@DiscriminatorValue("3")
public class JpaExtendedUser extends JpaUser implements ExtendedUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
