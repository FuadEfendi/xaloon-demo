package org.xaloon.wicket.demo.panel;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.xaloon.wicket.demo.page.SelectLanguagePage;

/**
 * @author vytautas r.
 *
 */
public class ChangeLocalePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 */
	public ChangeLocalePanel(String id) {
		super(id);
	}
	

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(createBookmarkableLink("spanish", "es", "ES"));
		add(createBookmarkableLink("english", "en", "UK"));
	}

	private BookmarkablePageLink<Void> createBookmarkableLink(String id, String languageId, String countryId) {
		PageParameters params = new PageParameters();
		params.add(SelectLanguagePage.LANGUAGE, languageId);
		params.add(SelectLanguagePage.COUNTRY, countryId);
		return new BookmarkablePageLink<Void>(id, SelectLanguagePage.class, params);
	}

}
