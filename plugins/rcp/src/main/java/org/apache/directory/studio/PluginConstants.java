/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.studio;


/**
 * This class contains all the Constants used in the Plugin.
 * Final reference -> class shouldn't be extended
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public final class PluginConstants
{

    /**
     *  Ensures no construction of this class, also ensures there is no need for final keyword above
     *  (Implicit super constructor is not visible for default constructor),
     *  but is still self documenting.
     */
    private PluginConstants()
    {
    }

    /** The Add Extension Action ID */
    public static final String ACTION_ADD_EXTENSION_ID = Activator.getDefault().getPluginProperties().getString(
        "Action_AddExtension_id" ); //$NON-NLS-1$

    /** The Manage Configuration Action ID */
    public static final String ACTION_MANAGE_CONFIGURATION_ID = Activator.getDefault().getPluginProperties().getString(
        "Action_ManageConfiguration_id" ); //$NON-NLS-1$

    /** The Open File Action ID */
    public static final String ACTION_OPEN_FILE_ID = Activator.getDefault().getPluginProperties().getString(
        "Action_OpenFile_id" ); //$NON-NLS-1$

    /** The Report A Bug Action ID */
    public static final String ACTION_REPORT_A_BUG_ID = Activator.getDefault().getPluginProperties().getString(
        "Action_ReportABug_id" ); //$NON-NLS-1$

    /** The Update ActionID */
    public static final String ACTION_UPDATE_ID = Activator.getDefault().getPluginProperties().getString(
        "Action_Update_id" ); //$NON-NLS-1$

}
