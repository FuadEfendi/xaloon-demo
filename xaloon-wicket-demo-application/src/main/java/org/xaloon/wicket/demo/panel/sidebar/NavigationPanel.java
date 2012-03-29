package org.xaloon.wicket.demo.panel.sidebar;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.xaloon.core.impl.plugin.tree.GenericTreeNode;
import org.xaloon.core.impl.plugin.tree.MenuItem;
import org.xaloon.wicket.plugin.menu.panel.DynamicMenuItemPanel;

public class NavigationPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NavigationPanel(String id, List<GenericTreeNode<MenuItem>> children) {
		super(id);
		add(new DynamicMenuItemPanel("dynamic-menu", children).setUseMenuDelimiter(false));
	}

}
