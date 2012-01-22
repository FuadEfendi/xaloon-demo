package org.xaloon.wicket.demo.page;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.xaloon.wicket.application.page.LayoutWebPage;
import org.xaloon.wicket.component.mount.annotation.MountPage;
import org.xaloon.wicket.demo.panel.PageNotFoundPanel;

@MountPage(value="/404", visible=false)
public class NotFoundPage extends LayoutWebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Panel getContentPanel(String id, PageParameters pageParameters) {
		return new PageNotFoundPanel(id);
	}
	
	@Override
	protected void setHeaders(WebResponse response) {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND); 
		super.setHeaders(response);
	}
	
	@Override
	public boolean isErrorPage() {
		return true;
	}
}
