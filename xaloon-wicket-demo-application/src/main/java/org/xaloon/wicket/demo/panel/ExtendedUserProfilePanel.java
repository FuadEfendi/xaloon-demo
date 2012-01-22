package org.xaloon.wicket.demo.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.xaloon.wicket.demo.extended.ExtendedUser;
import org.xaloon.wicket.plugin.user.panel.UserProfilePanel;

/**
 * @author vytautas r.
 *
 */
public class ExtendedUserProfilePanel extends UserProfilePanel<ExtendedUser> {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 * @param params
	 */
	public ExtendedUserProfilePanel(String id, PageParameters params) {
		super(id, params);
	}
	
	@Override
	protected void onFormInitialize(Form<ExtendedUser> profileForm) {
		profileForm.add(new TextField<String>("phoneNumber"));
	}
}
