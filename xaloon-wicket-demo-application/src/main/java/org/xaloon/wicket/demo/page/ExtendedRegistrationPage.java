package org.xaloon.wicket.demo.page;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.xaloon.wicket.component.mount.annotation.MountPage;
import org.xaloon.wicket.demo.extended.ExtendedRegistrationModel;
import org.xaloon.wicket.demo.panel.ExtendedRegistrationPanel;
import org.xaloon.wicket.plugin.user.page.UserRegistrationPage;

/**
 * @author vytautas r.
 *
 */
@MountPage(value = "/register", order = 10, visible = false)
public class ExtendedRegistrationPage extends UserRegistrationPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Panel getContentPanel(String id, PageParameters pageParameters) {
		return new ExtendedRegistrationPanel(id, new Model<ExtendedRegistrationModel>(new ExtendedRegistrationModel()));
	}
}
