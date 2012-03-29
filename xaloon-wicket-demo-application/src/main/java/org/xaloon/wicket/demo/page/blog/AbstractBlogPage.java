package org.xaloon.wicket.demo.page.blog;

import org.apache.wicket.markup.html.panel.Panel;
import org.xaloon.wicket.application.page.LayoutWebPage;
import org.xaloon.wicket.demo.panel.sidebar.BlogSidebarPanel;

public abstract class AbstractBlogPage extends LayoutWebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Panel sidebarPanel;
	
	@Override
	public Panel getSidebarPanel(String wicketIdSidebar) {
		if (sidebarPanel == null) {
			sidebarPanel = new BlogSidebarPanel(wicketIdSidebar);
		}
		return sidebarPanel;
	}
}
