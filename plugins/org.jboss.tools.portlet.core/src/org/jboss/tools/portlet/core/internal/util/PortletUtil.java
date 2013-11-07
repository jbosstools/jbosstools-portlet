/*************************************************************************************
 * Copyright (c) 2013 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.jboss.tools.portlet.core.internal.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.jboss.tools.portlet.core.IPortletConstants;

/**
 * 
 * @author snjeza
 *
 */
public class PortletUtil {

	private static final String IMPLEMENTATION_VERSION = "Implementation-Version"; //$NON-NLS-1$

	private static final String PORTLETBRIDGE_API = "portletbridge-api"; //$NON-NLS-1$

	private static final String PORTLETBRIDGE_CORE_API = "portletbridge-core-api"; //$NON-NLS-1$

	private static final String JAR = ".jar"; //$NON-NLS-1$

	
	public static int getPortletBridgeVersion(IProject project) {
		if (project == null || !project.isOpen()) {
			return 0;
		}
		IVirtualComponent component = ComponentCore.createComponent(project);
		IVirtualFolder rootFolder = component.getRootFolder();
		IContainer folder = rootFolder.getUnderlyingFolder();
		IContainer webinf = folder.getFolder(new Path(IPortletConstants.WEB_INF_LIB));
		String libResource = webinf.getLocation().toOSString();
		File libFile = new File(libResource);
		if (!libFile.exists()) {
			return 0;
		}
		File[] files = libFile.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				if (name != null && name.startsWith(PORTLETBRIDGE_API)
						&& name.endsWith(JAR)) {
					return true;
				}
				if (name != null && name.startsWith(PORTLETBRIDGE_CORE_API)
						&& name.endsWith(JAR)) {
					return true;
				}
				return false;
			}
		});
		if (files == null || files.length <= 0) {
			return 0;
		}
		File jarFile = files[0];
		if(jarFile.isFile()) {
			try {
				JarFile jar = new JarFile(jarFile);
				Attributes attributes = jar.getManifest().getMainAttributes();
				String versionString = attributes.getValue(IMPLEMENTATION_VERSION);
				if (versionString != null && versionString.trim().length() > 0) {
					StringTokenizer tokenizer = new StringTokenizer(versionString,"."); //$NON-NLS-1$
					if (tokenizer.hasMoreTokens()) {
						String firstNumber = tokenizer.nextToken();
						int version = 0;
						try {
							version = new Integer(firstNumber).intValue();
						} catch (NumberFormatException e) {
							// ignore
						}
						return version;
					}
				}
				
			} catch (IOException e) {
				return 0;
			}
		}
		return 0;
	}

}
