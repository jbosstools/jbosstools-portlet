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
package org.jboss.tools.portlet.core.internal;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.jboss.ide.eclipse.as.classpath.core.jee.AbstractClasspathContainer;

/**
 * 
 * @author snjeza
 *
 */
public abstract class BasePortletClasspathContainer extends
		AbstractClasspathContainer {

	protected static final String PORTLET_FOLDER = "portlet"; //$NON-NLS-1$
	public final static String SUFFIX = PORTLET_FOLDER;
	public final static String PREFIX = "org.jboss.tools.portlet.core"; //$NON-NLS-1$

	public BasePortletClasspathContainer(IJavaProject project, IPath path,
			String description, String suffix) {
		super(path, description, suffix,  project);
	}

	public IJavaProject getProject() {
		return javaProject;
	}
}
