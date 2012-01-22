package org.xaloon.wicket.demo.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.xaloon.wicket.demo.extended.ExtendedRegistrationModel;
import org.xaloon.wicket.demo.extended.ExtendedUser;
import org.xaloon.wicket.plugin.user.panel.RegistrationPanel;

/**
 * @author vytautas r.
 *
 */
public class ExtendedRegistrationPanel extends RegistrationPanel<ExtendedRegistrationModel, ExtendedUser> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 * @param model
	 */
	public ExtendedRegistrationPanel(String id, IModel<ExtendedRegistrationModel> model) {
		super(id, model);
	}

	@Override
	protected void onBeforeRegistration(ExtendedUser user, ExtendedRegistrationModel registration) {
		user.setPhoneNumber(registration.getPhoneNumber());
	}
	
	@Override
	protected void onFormInitialize(Form<ExtendedRegistrationModel> registrationForm) {
		registrationForm.add(createPhoneNumberField());
	}

	private Component createPhoneNumberField() {
		return new TextField<String>("phoneNumber");
	}
}
