package org.xaloon.wicket.demo.page;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.xaloon.wicket.component.mount.annotation.MountPage;
import org.xaloon.wicket.util.BotAgent;

/**
 * @author vytautas r.
 * 
 */
@MountPage(value = "/language/${" + SelectLanguagePage.LANGUAGE + "}/${" + SelectLanguagePage.COUNTRY + "}", visible = false)
public class SelectLanguagePage extends WebPage {

	private static final String REFERER = "Referer";


	private static final String USER_AGENT = "User-Agent";


	/**
	 * Locale language parameter
	 */
	public static final String LANGUAGE = "lang";


	/**
	 * Locale country parameter
	 */
	public static final String COUNTRY = "country";


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param pageParameters
	 */
	public SelectLanguagePage(PageParameters pageParameters) {
		HttpServletRequest request = (HttpServletRequest)((WebRequest)RequestCycle.get().getRequest()).getContainerRequest();

		final String agent = request.getHeader(USER_AGENT);

		if (pageParameters != null) {
			String languageId = pageParameters.get(SelectLanguagePage.LANGUAGE).toString();
			String countryId = pageParameters.get(SelectLanguagePage.COUNTRY).toString();
			if (!StringUtils.isEmpty(languageId) && !StringUtils.isEmpty(countryId)) {
				WebSession.get().setLocale(new Locale(languageId, countryId));
				if (!BotAgent.isAgent(agent)) {
					WebSession.get().bind();
				}
			}
		}
		String referer = request.getHeader(REFERER);
		if (!StringUtils.isEmpty(referer) && isValid(referer)) {
			throw new RedirectToUrlException(referer);
		} else {
			setResponsePage(WebApplication.get().getHomePage());
		}

	}

	private boolean isValid(String referer) {
		return !referer.contains("ILinkListener");
	}
}
