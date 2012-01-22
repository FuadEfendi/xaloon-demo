package org.xaloon.wicket.demo.page;

import javax.annotation.security.RolesAllowed;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.xaloon.core.api.security.SecurityRoles;
import org.xaloon.wicket.component.mount.annotation.MountPage;
import org.xaloon.wicket.demo.panel.ExtendedUserProfilePanel;
import org.xaloon.wicket.plugin.user.page.UserProfilePage;

/**
 * @author vytautas r.
 *
 */
@MountPage(value = "/profile", order = 10)
@RolesAllowed({ SecurityRoles.AUTHENTICATED_USER })
public class ExtendedUserProfilePage extends UserProfilePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Panel getContentPanel(String id, PageParameters pageParameters) {
		return new ExtendedUserProfilePanel(id, pageParameters);
	}
}
