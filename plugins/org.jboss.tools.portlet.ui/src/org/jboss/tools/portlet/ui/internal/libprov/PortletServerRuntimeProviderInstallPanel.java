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
package org.jboss.tools.portlet.ui.internal.libprov;

import static org.eclipse.wst.common.project.facet.ui.internal.util.GridLayoutUtil.gl;

import org.eclipse.jst.common.project.facet.ui.libprov.LibraryProviderOperationPanel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.jboss.tools.portlet.ui.Messages;

/**
 * 
 * @author snjeza
 *
 */
public class PortletServerRuntimeProviderInstallPanel extends LibraryProviderOperationPanel {

	@Override
    public Control createControl( final Composite parent )
    {
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( gl( 1, 0, 0 ) );
        
        addMessage(composite);
        
        return composite;
    }

	private void addMessage(final Composite composite) {
		final Link link = new Link( composite, SWT.WRAP );
        final GridData data = new GridData( SWT.FILL, SWT.BEGINNING, true, false );
        data.widthHint = 300;
        link.setLayoutData( data );
        link.setText( Messages.PortletServerRuntimeProviderInstallPanel_The_targeted_runtime_contains_a_portlet_library );
	}
}
