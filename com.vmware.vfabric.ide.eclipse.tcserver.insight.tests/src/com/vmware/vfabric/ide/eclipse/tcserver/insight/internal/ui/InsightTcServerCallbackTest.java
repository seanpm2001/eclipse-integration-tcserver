/*******************************************************************************
 *  Copyright (c) 2012 VMware, Inc.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *      VMware, Inc. - initial API and implementation
 *******************************************************************************/
package com.vmware.vfabric.ide.eclipse.tcserver.insight.internal.ui;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import com.vmware.vfabric.ide.eclipse.tcserver.insight.internal.ui.InsightTcServerCallback;

/**
 * @author Steffen Pingel
 */
public class InsightTcServerCallbackTest extends TestCase {

	public void testAddInsightBase() {
		IPath path = new Path("/");
		String result;

		result = InsightTcServerCallback.addInsightBase("", path);
		assertEquals(" -Dinsight.base=\"/\"", result);

		result = InsightTcServerCallback.addInsightBase("arg", path);
		assertEquals("arg -Dinsight.base=\"/\"", result);
	}

	public void testAddInsightBaseReplace() {
		IPath path = new Path("/");
		String result;

		result = InsightTcServerCallback.addInsightBase("-Dinsight.base=\"old\"", path);
		assertEquals("-Dinsight.base=\"/\"", result);

		path = new Path("com  plex/path");
		result = InsightTcServerCallback.addInsightBase("ab  -Dinsight.base=\"old\" cd", path);
		assertEquals("ab  -Dinsight.base=\"com  plex/path\" cd", result);

		path = new Path("/");
		result = InsightTcServerCallback.addInsightBase("-Dinsight.base=\"/space in path/file\"", path);
		assertEquals("-Dinsight.base=\"/\"", result);
	}

	public void testAddInsightBaseMultipleArguments() {
		IPath path = new Path("/");
		String result;

		result = InsightTcServerCallback.addInsightBase("-DargOne=\"one\"", path);
		assertEquals("-DargOne=\"one\" -Dinsight.base=\"/\"", result);

		result = InsightTcServerCallback.addInsightBase("-DargOne=\"one\" -Dinsight.base=\"old\" -DargOne=\"two\"",
				path);
		assertEquals("-DargOne=\"one\" -Dinsight.base=\"/\" -DargOne=\"two\"", result);
	}

}
