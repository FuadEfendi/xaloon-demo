package org.xaloon.wicket.demo.panel.sidebar;

import org.apache.wicket.markup.html.panel.Panel;
import org.xaloon.wicket.plugin.blog.panel.BlogEntryListTitlesPanel;
import org.xaloon.wicket.plugin.blog.panel.BlogListOptions;
import org.xaloon.wicket.plugin.blog.panel.CategoryPanel;

public class BlogSidebarPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlogSidebarPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new BlogEntryListTitlesPanel("recent-blogs", new BlogListOptions().setMaxBlogEntriesCount(5)));
		add(new CategoryPanel("blog-categories"));
	}
}
