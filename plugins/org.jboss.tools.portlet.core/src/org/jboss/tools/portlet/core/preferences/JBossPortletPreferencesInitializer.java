/*************************************************************************************
 * Copyright (c) 2008-2014 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.jboss.tools.portlet.core.preferences;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.jboss.tools.portlet.core.PortletCoreActivator;

/**
 * 
 * @author snjeza
 *
 */
public class JBossPortletPreferencesInitializer extends
		AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		Preferences preferences = PortletCoreActivator.getDefault().getPluginPreferences();
		preferences.setDefault(
				PortletCoreActivator.CHECK_RUNTIMES,
				PortletCoreActivator.DEFAULT_CHECK_RUNTIMES);
	}

}
