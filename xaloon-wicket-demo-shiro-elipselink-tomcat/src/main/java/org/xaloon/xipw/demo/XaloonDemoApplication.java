/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xaloon.xipw.demo;

import javax.inject.Inject;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IRequestCycleSettings.RenderStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xaloon.core.api.config.Configuration;
import org.xaloon.core.api.plugin.Plugin;
import org.xaloon.core.api.plugin.resource.ResourceRepositoryListener;
import org.xaloon.core.api.user.model.User;
import org.xaloon.wicket.component.inject.spring.CdiAnnotationResolver;
import org.xaloon.wicket.component.inject.spring.CdiComponentInjector;
import org.xaloon.wicket.component.inject.spring.SpringAnnotationResolver;
import org.xaloon.wicket.component.inject.spring.SpringBeanAnnotationResolver;
import org.xaloon.wicket.component.inject.spring.SpringBeanLocatorAdapter;
import org.xaloon.wicket.component.mount.impl.SpringMountScanner;
import org.xaloon.wicket.component.mount.impl.TrailingSlashPageMountingListener;
import org.xaloon.wicket.component.security.AuthenticatedWebApplication;
import org.xaloon.wicket.component.security.AuthenticatedWebSession;
import org.xaloon.wicket.component.sitemap.SiteMap;
import org.xaloon.wicket.demo.extended.JpaExtendedUser;
import org.xaloon.wicket.demo.init.DemoFacade;
import org.xaloon.wicket.plugin.blog.BlogUserListener;
import org.xaloon.wicket.plugin.blog.rss.BlogRssFeed;
import org.xaloon.wicket.plugin.image.plugin.GalleryUserListener;

/**
 * @author vytautas r.
 */
public class XaloonDemoApplication extends AuthenticatedWebApplication {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(XaloonDemoApplication.class);

	@Inject
	private DemoFacade demoFacade;

	@Override
	public Class<? extends Page> getHomePage() {
		return org.xaloon.wicket.demo.page.IndexPage.class;
	}

	@Override
	protected void init() {
		super.init();
		// Custom settings
		getRequestLoggerSettings().setRequestLoggerEnabled(true);
		getRequestCycleSettings().setRenderStrategy(
				RenderStrategy.ONE_PASS_RENDER);
		demoFacade.initDemoData();

		mountPage("/sitemap.xml", SiteMap.class);
		mountPage("/blog-rss", BlogRssFeed.class);
		
		//setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig(8080, 8443)));
	}

	@Override
	protected void onLoadConfiguration(Configuration config) {
		// Add action before saving resource
		config.getResourceRepositoryListeners()
				.add(new ResourceRepositoryListener() {
					private static final long serialVersionUID = 1L;

					public void onBeforeSaveProperty(Plugin plugin,
							String propertyKey, Object value) {
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug(plugin.getName() + "\t" + propertyKey
									+ "\t" + value);
						}
					}

					public void onAfterSaveProperty(Plugin plugin,
							String propertyKey) {
					}
				});

		config.setBeanLocatorAdapter(
				new SpringBeanLocatorAdapter());
		config.getUserListenerCollection().add(new BlogUserListener());
		config.getUserListenerCollection().add(new GalleryUserListener());
	}

	@Override
	protected void initComponentInjector() {
		SpringAnnotationResolver[] resolvers = new SpringAnnotationResolver[] {
				new CdiAnnotationResolver(), new SpringBeanAnnotationResolver() };
		getComponentInstantiationListeners().add(
				new CdiComponentInjector(this, resolvers));
		Injector.get().inject(this);
	}

	@Override
	protected Class<? extends User> getPersistedUserImplementation() {
		return JpaExtendedUser.class;
	}

	@Override
	protected void onAddMountScannerListener(SpringMountScanner scanner) {
		super.onAddMountScannerListener(scanner);
		scanner.addMountScannerListener(new TrailingSlashPageMountingListener());
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new AuthenticatedWebSession(request, false);
	}
}
