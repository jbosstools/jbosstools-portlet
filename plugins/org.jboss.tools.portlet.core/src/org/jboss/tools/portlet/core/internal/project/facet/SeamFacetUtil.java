package org.jboss.tools.portlet.core.internal.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/*************************************************************************************
 * Copyright (c) 2015 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/

/**
 * Utility class to deal with the Seam Facet, whether Seam Tools is available or not
 * 
 * @author Fred Bricon
 */
public class SeamFacetUtil {

	private static final String JST_SEAM_FACET = "jst.seamxxxx"; //$NON-NLS-1$
	
	public static final IProjectFacet SEAM_FACET; 
	
	static {
		if (!ProjectFacetsManager.isProjectFacetDefined(JST_SEAM_FACET)) {
			SEAM_FACET = null;
		} else {
			SEAM_FACET = ProjectFacetsManager.getProjectFacet(JST_SEAM_FACET);
		}
	}
	
	private SeamFacetUtil() {}
	
	/**
	 * Checks if the Seam Facet is available (i.e, Seam Tooling is installed)
	 */
	public static boolean isSeamAvailable() {
		return SEAM_FACET != null;
	}

	/**
	 * Checks if the project has the Seam Facet (if Seam Tooling is installed)
	 */
	public static boolean hasSeamFacet(IProject project) {
		if (isSeamAvailable()) {
			try {
				return FacetedProjectFramework.hasProjectFacet(project, JST_SEAM_FACET);
			} catch (CoreException O_o) {}
		}
		return false;
	}
	
}
