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
package org.xaloon.wicket.demo.page.layout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.xaloon.core.api.tree.TreeNode;
import org.xaloon.core.impl.plugin.tree.GenericTreeNode;
import org.xaloon.core.impl.plugin.tree.MenuItem;
import org.xaloon.wicket.application.page.LayoutComponentInitializer;
import org.xaloon.wicket.application.page.LayoutWebPage;
import org.xaloon.wicket.demo.page.CruisesPage;
import org.xaloon.wicket.demo.page.HotelsPage;
import org.xaloon.wicket.demo.page.IndexPage;
import org.xaloon.wicket.demo.panel.ChangeLocalePanel;
import org.xaloon.wicket.plugin.addthis.panel.AddThisPanel;
import org.xaloon.wicket.plugin.blog.page.CreateBlogEntryPage;
import org.xaloon.wicket.plugin.google.GoogleWebMasterMarkupContainer;
import org.xaloon.wicket.plugin.google.PlusOneSize;
import org.xaloon.wicket.plugin.google.panel.GoogleAnalyticsPanel;
import org.xaloon.wicket.plugin.google.panel.GooglePlusOnePanel;
import org.xaloon.wicket.plugin.menu.DynamicMenuFacade;
import org.xaloon.wicket.plugin.menu.MenuContainer;
import org.xaloon.wicket.plugin.menu.panel.DynamicMenuItemPanel;
import org.xaloon.wicket.plugin.user.panel.HeaderPanel;

/**
 * @author vytautas r.
 * 
 */
@Named
public class DemoLayoutComponentInitializer implements LayoutComponentInitializer {
	private static final long serialVersionUID = 1L;

	private static final String WICKET_ID_SIDEBAR = "sidebar";

	private static final List<GenericTreeNode<MenuItem>> firstLevelMenuItems = new ArrayList<GenericTreeNode<MenuItem>>();

	@Inject
	private DynamicMenuFacade dynamicMenuFacade;

	private List<GenericTreeNode<MenuItem>> getTopLevelMenuItems(LayoutWebPage layoutWebPage) {
		if (firstLevelMenuItems.isEmpty()) {
			firstLevelMenuItems.add(dynamicMenuFacade.getMenu(IndexPage.class));
			firstLevelMenuItems.add(dynamicMenuFacade.getMenu(CruisesPage.class));
			firstLevelMenuItems.add(dynamicMenuFacade.getMenu(HotelsPage.class));
		}
		return firstLevelMenuItems;
	}

	public void onBeforeRender(LayoutWebPage layoutWebPage) {
		if (layoutWebPage.get(WICKET_ID_SIDEBAR) != null) {
			layoutWebPage.remove(WICKET_ID_SIDEBAR);
		}

		// Take parent of this menu item as we want to display all menu of current level
		TreeNode<MenuItem> parentMenuTreeItem = dynamicMenuFacade.getParent(layoutWebPage.getClass());
		if (parentMenuTreeItem == null || isInIgnoreList(layoutWebPage)) {
			layoutWebPage.add(new Label(WICKET_ID_SIDEBAR, new Model<String>("")).setVisible(false));
			return;
		}

		// Do not duplicate root menu
		if (parentMenuTreeItem.getParent() != null && parentMenuTreeItem.hasMoreThanOneChildren()) {
			layoutWebPage.add(new DynamicMenuItemPanel(WICKET_ID_SIDEBAR, parentMenuTreeItem.getChildren()).setUseMenuDelimiter(false));
		} else {
			layoutWebPage.add(new Label(WICKET_ID_SIDEBAR, new Model<String>("")).setVisible(false));
		}
	}

	public void onInitialize(LayoutWebPage layoutWebPage, Panel content) {
		layoutWebPage.add(new HeaderPanel("header-panel"));
		layoutWebPage.add(new DynamicMenuItemPanel("top-flat-menu", getTopLevelMenuItems(layoutWebPage)));

		layoutWebPage.add(new GoogleWebMasterMarkupContainer("webmaster-keys"));

		// add dynamic menu
		layoutWebPage.add(new MenuContainer("dynamic-menu-container"));

		// add language selector
		layoutWebPage.add(new ChangeLocalePanel("change-locale"));

		// addthis panel
		layoutWebPage.add(new AddThisPanel("add-this-panel"));

		// plusone panel
		layoutWebPage.add(new GooglePlusOnePanel("plus-one", PlusOneSize.SMALL, "en-GB"));

		// add google analytics panel
		layoutWebPage.add(new GoogleAnalyticsPanel("google-analytics-panel"));

		if (hasSidebarMenu(layoutWebPage)) {
			content.add(AttributeModifier.replace("class", "content_sidebar"));
		}
	}

	protected boolean hasSidebarMenu(LayoutWebPage layoutWebPage) {
		if (isInIgnoreList(layoutWebPage)) {
			return false;
		}
		TreeNode<MenuItem> parentMenuItem = dynamicMenuFacade.getParent(layoutWebPage.getClass());
		return (parentMenuItem != null && parentMenuItem.getParent() != null && parentMenuItem.hasMoreThanOneChildren());
	}

	private boolean isInIgnoreList(LayoutWebPage layoutWebPage) {
		return CreateBlogEntryPage.class.equals(layoutWebPage.getClass());
	}

}
