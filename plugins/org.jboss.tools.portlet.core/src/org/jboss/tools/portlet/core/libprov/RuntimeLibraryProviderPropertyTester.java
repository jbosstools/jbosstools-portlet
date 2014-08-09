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
package org.jboss.tools.portlet.core.libprov;

import java.io.File;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jst.common.project.facet.core.libprov.EnablementExpressionContext;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectBase;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.internal.facets.FacetUtil;
import org.jboss.tools.portlet.core.internal.PortletRuntimeComponentProvider;

/**
 * 
 * @author snjeza
 *
 */
public final class RuntimeLibraryProviderPropertyTester extends PropertyTester {
	
	public boolean test(final Object receiver, final String property,
			final Object[] args, final Object value) {
		if (receiver instanceof EnablementExpressionContext) {
			EnablementExpressionContext context = (EnablementExpressionContext) receiver;
			IFacetedProjectBase facetedProject = context.getFacetedProject();
			org.eclipse.wst.common.project.facet.core.runtime.IRuntime primaryRuntime = facetedProject.getPrimaryRuntime();
			if (primaryRuntime == null) {
				return false;
			}
			IRuntime runtime = FacetUtil.getRuntime(primaryRuntime);
			if (runtime != null) {
				if ("isJSFPortletRuntime".equals(property)) { //$NON-NLS-1$
					String id = runtime.getRuntimeType().getId();
					if (! "org.jboss.ide.eclipse.as.runtime.eap.43".equals(id) && ! "org.jboss.ide.eclipse.as.runtime.42".equals(id)) {  //$NON-NLS-1$//$NON-NLS-2$
						return false;
					}
				}
				File location = runtime.getLocation().toFile();
				return PortletRuntimeComponentProvider.isPortalPresent(location, runtime, property);
			}
		}
		return false;
	}

}
