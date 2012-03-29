package org.xaloon.wicket.demo.page.blog;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.xaloon.wicket.component.mount.annotation.MountPage;
import org.xaloon.wicket.plugin.blog.BlogPageConstants;
import org.xaloon.wicket.plugin.blog.panel.BlogEntryPanel;

/**
 * This is default example of Blog entry page.
 * 
 * Usually you should use your own page, just injecting the panel. Otherwise you may override BlogEntryPage.html file and customize your own template.
 * 
 * Also this page is useful when using VirtualPageFactory.
 * 
 * @author vytautas r.
 * 
 */
@MountPage(value = "/blog/${" + BlogPageConstants.BLOG_USERNAME + "}/${" + BlogPageConstants.BLOG_PATH + "}", visible = false, weight=1)
public class DemoBlogEntryNoDatePage extends AbstractBlogPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Panel getContentPanel(String id, PageParameters pageParameters) {
		return new BlogEntryPanel(id, pageParameters);
	}

}
