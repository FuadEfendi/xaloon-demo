package org.xaloon.wicket.demo.page.blog;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.xaloon.wicket.component.mount.annotation.MountPage;
import org.xaloon.wicket.plugin.blog.BlogPageConstants;
import org.xaloon.wicket.plugin.blog.panel.BlogEntryPanel;

@MountPage(value = "/blog/${" + BlogPageConstants.BLOG_USERNAME + "}/${" + BlogPageConstants.BLOG_YEAR + "}/${" + BlogPageConstants.BLOG_MONTH
		+ "}/${" + BlogPageConstants.BLOG_DAY + "}/${" + BlogPageConstants.BLOG_PATH + "}", visible = false, weight=1)
public class DemoBlogEntryPage extends AbstractBlogPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Panel getContentPanel(String id, PageParameters pageParameters) {
		return new BlogEntryPanel(id, pageParameters);
	}
}
