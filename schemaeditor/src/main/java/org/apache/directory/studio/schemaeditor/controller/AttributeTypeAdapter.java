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
package org.apache.directory.studio.schemaeditor.controller;


/**
 * This adapter class provides default implementations for the methods 
 * described by the AttributeTypeListener interface.
 * <p>
 * Classes that wish to deal with attribute type events can extend this class 
 * and override only the methods which they are interested in. 
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public abstract class AttributeTypeAdapter implements AttributeTypeListener
{
    /* (non-Javadoc)
     * @see org.apache.directory.studio.schemaeditor.model.AttributeTypeListener#attributeTypeModified()
     */
    public void attributeTypeModified()
    {
    }


    /* (non-Javadoc)
     * @see org.apache.directory.studio.schemaeditor.model.AttributeTypeListener#attributeTypeRemoved()
     */
    public void attributeTypeRemoved()
    {
    }
}
