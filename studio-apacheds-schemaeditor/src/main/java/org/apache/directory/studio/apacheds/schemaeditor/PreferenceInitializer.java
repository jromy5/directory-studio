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
package org.apache.directory.studio.apacheds.schemaeditor;


import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * This class initializes the preferences of the plug-in.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$, $Date$
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer
{
    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();

        // DifferencesWidget
        store.setDefault( PluginConstants.PREFS_DIFFERENCES_WIDGET_GROUPING,
            PluginConstants.PREFS_DIFFERENCES_WIDGET_GROUPING_PROPERTY );

        // Schema View Preference Page
        store.setDefault( PluginConstants.PREFS_SCHEMA_VIEW_LABEL, PluginConstants.PREFS_SCHEMA_VIEW_LABEL_ALL_ALIASES );
        store.setDefault( PluginConstants.PREFS_SCHEMA_VIEW_ABBREVIATE, true );
        store.setDefault( PluginConstants.PREFS_SCHEMA_VIEW_ABBREVIATE_MAX_LENGTH, "50" ); //$NON-NLS-1$
        store.setDefault( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_DISPLAY, true );
        store.setDefault( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL,
            PluginConstants.PREFS_SCHEMA_VIEW_LABEL_OID );
        store.setDefault( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_ABBREVIATE, false );
        store.setDefault( PluginConstants.PREFS_SCHEMA_VIEW_SECONDARY_LABEL_ABBREVIATE_MAX_LENGTH, "50" ); //$NON-NLS-1$
    }
}
