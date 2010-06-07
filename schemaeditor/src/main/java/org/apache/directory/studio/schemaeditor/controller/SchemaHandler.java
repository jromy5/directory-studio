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


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.directory.shared.ldap.schema.SchemaObject;
import org.apache.directory.studio.schemaeditor.model.AttributeTypeImpl;
import org.apache.directory.studio.schemaeditor.model.MatchingRuleImpl;
import org.apache.directory.studio.schemaeditor.model.ObjectClassImpl;
import org.apache.directory.studio.schemaeditor.model.Schema;
import org.apache.directory.studio.schemaeditor.model.SyntaxImpl;


/**
 * This class represents the SchemaHandler.
 * <p>
 * It used to handle the whole Schema (including schemas, attribute types, 
 * object classes, matching rules and syntaxes).
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class SchemaHandler
{
    //
    // The Lists
    //
    /** The schemas List */
    private List<Schema> schemasList;
    /** The attribute types List */
    private List<AttributeTypeImpl> attributeTypesList;
    /** The matching rules List */
    private List<MatchingRuleImpl> matchingRulesList;
    /** The object classes List */
    private List<ObjectClassImpl> objectClassesList;
    /** The syntaxes List */
    private List<SyntaxImpl> syntaxesList;

    //
    // The MultiMap (for fast searching)
    //
    /** The schemas MultiMap */
    private MultiMap schemasMap;
    /** The attribute types MultiMap */
    private MultiMap attributeTypesMap;
    /** The matching rules MultiMap */
    private MultiMap matchingRulesMap;
    /** The object classes MultiMap */
    private MultiMap objectClassesMap;
    /** The syntaxes MultiMap */
    private MultiMap syntaxesMap;

    //
    // The Listeners Lists
    //
    private List<SchemaHandlerListener> schemaHandlerListeners;
    private MultiValueMap schemaListeners;
    private MultiValueMap attributeTypeListeners;
    private MultiValueMap objectClassListeners;


    /**
     * Creates a new instance of SchemaHandler.
     */
    public SchemaHandler()
    {
        // Lists
        schemasList = new ArrayList<Schema>();
        attributeTypesList = new ArrayList<AttributeTypeImpl>();
        matchingRulesList = new ArrayList<MatchingRuleImpl>();;
        objectClassesList = new ArrayList<ObjectClassImpl>();
        syntaxesList = new ArrayList<SyntaxImpl>();

        // Maps
        schemasMap = new MultiValueMap();
        attributeTypesMap = new MultiValueMap();
        matchingRulesMap = new MultiValueMap();
        objectClassesMap = new MultiValueMap();
        syntaxesMap = new MultiValueMap();

        // Listeners
        schemaHandlerListeners = new ArrayList<SchemaHandlerListener>();
        schemaListeners = new MultiValueMap();
        attributeTypeListeners = new MultiValueMap();
        objectClassListeners = new MultiValueMap();
    }


    /**
     * Gets the List of all the attribute types.
     *
     * @return
     *      the List of all the attribute types
     */
    public List<AttributeTypeImpl> getAttributeTypes()
    {
        return attributeTypesList;
    }


    /**
     * Gets the List of all the matching rules.
     *
     * @return
     *      the List of all the matching rules
     */
    public List<MatchingRuleImpl> getMatchingRules()
    {
        return matchingRulesList;
    }


    /**
     * Gets the List of all the object classes.
     *
     * @return
     *      the List of all the object classes
     */
    public List<ObjectClassImpl> getObjectClasses()
    {
        return objectClassesList;
    }


    /**
     * Gets the List of all the schemas.
     *
     * @return
     *      the List of all the schemas
     */
    public List<Schema> getSchemas()
    {
        return schemasList;
    }


    /**
     * Gets the List of all the matching rules.
     *
     * @return
     *      the List of all the matching rules
     */
    public List<SyntaxImpl> getSyntaxes()
    {
        return syntaxesList;
    }


    /**
     * Gets an attribute type identified by an OID, or an alias.
     *
     * @param id
     *      an OID or an alias
     * @return
     *      the corresponding attribute type, or null if no one is found
     */
    public AttributeTypeImpl getAttributeType( String id )
    {
        List<?> list = getAttributeTypeList( id.toLowerCase() );

        if ( ( list != null ) && ( list.size() >= 1 ) )
        {
            return ( AttributeTypeImpl ) list.get( 0 );
        }
        else
        {
            return null;
        }
    }


    /**
     * Get the attribute type(s) List identified by an OID, or an alias.
     *
     * @param id
     *      an OID or an alias
     * @return
     *      the corresponding attribute type(s) List or null if no one is found
     */
    public List<?> getAttributeTypeList( String id )
    {
        return ( List<?> ) attributeTypesMap.get( id.toLowerCase() );
    }


    /**
     * Gets a matching rule identified by an OID, or an alias.
     *
     * @param id
     *      an OID or an alias
     * @return
     *      the corresponding matching rule, or null if no one is found
     */
    public MatchingRuleImpl getMatchingRule( String id )
    {
        List<?> list = getMatchingRuleList( id.toLowerCase() );

        if ( ( list != null ) && ( list.size() >= 1 ) )
        {
            return ( MatchingRuleImpl ) list.get( 0 );
        }
        else
        {
            return null;
        }
    }


    /**
     * Gets a matching rule(s) List identified by an OID, or an alias.
     *
     * @param id
     *      an OID or an alias
     * @return
     *      the corresponding matching rule(s) List, or null if no one is found
     */
    public List<?> getMatchingRuleList( String id )
    {
        return ( List<?> ) matchingRulesMap.get( id.toLowerCase() );
    }


    /**
     * Gets an object class identified by an OID, or an alias.
     *
     * @param id
     *      an OID or an alias
     * @return
     *      the corresponding object class, or null if no one is found
     */
    public ObjectClassImpl getObjectClass( String id )
    {
        List<?> list = getObjectClassList( id.toLowerCase() );

        if ( ( list != null ) && ( list.size() >= 1 ) )
        {
            return ( ObjectClassImpl ) list.get( 0 );
        }
        else
        {
            return null;
        }
    }


    /**
     * Gets an object class(es) List identified by an OID, or an alias.
     *
     * @param id
     *      an OID or an alias
     * @return
     *      the corresponding object class(es) List, or null if no one is found
     */
    public List<?> getObjectClassList( String id )
    {
        return ( List<?> ) objectClassesMap.get( id.toLowerCase() );
    }


    /**
     * Gets a schema identified by a name.
     *
     * @param name
     *      a name
     * @return
     *      the corresponding schema, or null if no one is found
     */
    public Schema getSchema( String name )
    {
        List<?> list = getSchemaList( name.toLowerCase() );

        if ( ( list != null ) && ( list.size() >= 1 ) )
        {
            return ( Schema ) list.get( 0 );
        }
        else
        {
            return null;
        }
    }


    /**
     * Gets a schema(s) List identified by a name.
     *
     * @param name
     *      a name
     * @return
     *      the corresponding schema(s) List, or null if no one is found
     */
    public List<?> getSchemaList( String name )
    {
        return ( List<?> ) schemasMap.get( name.toLowerCase() );
    }


    /**
     * Gets a syntax identified by an OID, or an alias.
     *
     * @param id
     *      an OID or an alias
     * @return
     *      the corresponding syntax, or null if no one is found
     */
    public SyntaxImpl getSyntax( String id )
    {
        List<?> list = getSyntaxList( id.toLowerCase() );

        if ( ( list != null ) && ( list.size() >= 1 ) )
        {
            return ( SyntaxImpl ) list.get( 0 );
        }
        else
        {
            return null;
        }
    }


    /**
     * Gets a syntax(es) List identified by an OID, or an alias.
     *
     * @param id
     *      an OID or an alias
     * @return
     *      the corresponding syntax(es) List, or null if no one is found
     */
    public List<?> getSyntaxList( String id )
    {
        return ( List<?> ) syntaxesMap.get( id.toLowerCase() );
    }


    /**
     * Adds a SchemaHandlerListener.
     *
     * @param listener
     *      the listener
     */
    public void addListener( SchemaHandlerListener listener )
    {
        if ( !schemaHandlerListeners.contains( listener ) )
        {
            schemaHandlerListeners.add( listener );
        }
    }


    /**
     * Removes a SchemaHandlerListener.
     *
     * @param listener
     *      the listener
     */
    public void removeListener( SchemaHandlerListener listener )
    {
        schemaHandlerListeners.remove( listener );
    }


    /**
     * Adds a SchemaListener to the given schema.
     *
     * @param schema
     *      the schema
     * @param listener
     *      the listener
     */
    public void addListener( Schema schema, SchemaListener listener )
    {
        if ( !schemaListeners.containsValue( schema, listener ) )
        {
            schemaListeners.put( schema, listener );
        }
    }


    /**
     * Removes a SchemaListener to the given schema.
     *
     * @param schema
     *      the schema
     * @param listener
     *      the listener
     */
    public void removeListener( Schema schema, SchemaListener listener )
    {
        schemaListeners.remove( schema, listener );
    }


    /**
     * Adds an AttributeTypeListener to the given attribute type.
     *
     * @param at
     *      the attribute type
     * @param listener
     *      the listener
     */
    public void addListener( AttributeTypeImpl at, AttributeTypeListener listener )
    {
        if ( !attributeTypeListeners.containsValue( at, listener ) )
        {
            attributeTypeListeners.put( at, listener );
        }
    }


    /**
     * Removes an AttributeTypeListener to the given attribute type.
     *
     * @param at
     *      the attribute type
     * @param listener
     *      the listener
     */
    public void removeListener( AttributeTypeImpl at, AttributeTypeListener listener )
    {
        attributeTypeListeners.remove( at, listener );
    }


    /**
     * Adds an ObjectClassListener to the given object class.
     *
     * @param oc
     *      the object class
     * @param listener
     *      the listener
     */
    public void addListener( ObjectClassImpl oc, ObjectClassListener listener )
    {
        if ( !objectClassListeners.containsValue( oc, listener ) )
        {
            objectClassListeners.put( oc, listener );
        }
    }


    /**
     * Removes an ObjectClassListener to the given object class.
     *
     * @param oc
     *      the object class
     * @param listener
     *      the listener
     */
    public void removeListener( ObjectClassImpl oc, ObjectClassListener listener )
    {
        objectClassListeners.remove( oc, listener );
    }


    /**
     * Adds a schema
     *
     * @param schema
     *      the schema
     */
    public void addSchema( Schema schema )
    {
        // Adding the schema
        schemasList.add( schema );
        schemasMap.put( schema.getName().toLowerCase(), schema );

        // Adding its attribute types
        for ( AttributeTypeImpl at : schema.getAttributeTypes() )
        {
            addSchemaObject( at );
        }

        // Adding its matching rules
        for ( MatchingRuleImpl mr : schema.getMatchingRules() )
        {
            addSchemaObject( mr );
        }

        // Adding its object classes
        for ( ObjectClassImpl oc : schema.getObjectClasses() )
        {
            addSchemaObject( oc );
        }

        // Adding its syntaxes
        for ( SyntaxImpl syntax : schema.getSyntaxes() )
        {
            addSchemaObject( syntax );
        }

        notifySchemaAdded( schema );
    }


    /**
     * Adds the given SchemaObject to the corresponding List and Map
     *
     * @param object
     *      the SchemaObject
     */
    private void addSchemaObject( SchemaObject object )
    {
        if ( object instanceof AttributeTypeImpl )
        {
            AttributeTypeImpl at = ( AttributeTypeImpl ) object;
            attributeTypesList.add( at );
            String[] names = at.getNamesRef();
            if ( names != null )
            {
                for ( String name : names )
                {
                    attributeTypesMap.put( name.toLowerCase(), at );
                }
            }
            attributeTypesMap.put( at.getOid(), at );
        }
        else if ( object instanceof MatchingRuleImpl )
        {
            MatchingRuleImpl mr = ( MatchingRuleImpl ) object;
            matchingRulesList.add( mr );
            String[] names = mr.getNamesRef();
            if ( names != null )
            {
                for ( String name : names )
                {
                    matchingRulesMap.put( name.toLowerCase(), mr );
                }
            }
            matchingRulesMap.put( mr.getOid(), mr );
        }
        else if ( object instanceof ObjectClassImpl )
        {
            ObjectClassImpl oc = ( ObjectClassImpl ) object;
            objectClassesList.add( oc );
            String[] names = oc.getNamesRef();
            if ( names != null )
            {
                for ( String name : names )
                {
                    objectClassesMap.put( name.toLowerCase(), oc );
                }
            }
            objectClassesMap.put( oc.getOid(), oc );
        }
        else if ( object instanceof SyntaxImpl )
        {
            SyntaxImpl syntax = ( SyntaxImpl ) object;
            syntaxesList.add( syntax );
            String[] names = syntax.getNamesRef();
            if ( names != null )
            {
                for ( String name : names )
                {
                    syntaxesMap.put( name.toLowerCase(), syntax );
                }
            }
            syntaxesMap.put( syntax.getOid(), syntax );
        }
    }


    /**
     * Removes the given schema. 
     *
     * @param schema
     *      the schema
     */
    public void removeSchema( Schema schema )
    {
        // Removing the schema
        schemasList.remove( schema );
        schemasMap.remove( schema.getName().toLowerCase() );

        // Removing its attribute types
        for ( AttributeTypeImpl at : schema.getAttributeTypes() )
        {
            removeSchemaObject( at );
        }

        // Removing its matching rules
        for ( MatchingRuleImpl mr : schema.getMatchingRules() )
        {
            removeSchemaObject( mr );
        }

        // Removing its object classes
        for ( ObjectClassImpl oc : schema.getObjectClasses() )
        {
            removeSchemaObject( oc );
        }

        // Removing its syntaxes
        for ( SyntaxImpl syntax : schema.getSyntaxes() )
        {
            removeSchemaObject( syntax );
        }

        notifySchemaRemoved( schema );
    }


    /**
     * Removes the given SchemaObject to the corresponding List and Map
     *
     * @param object
     *      the SchemaObject
     */
    private void removeSchemaObject( SchemaObject object )
    {
        if ( object instanceof AttributeTypeImpl )
        {
            AttributeTypeImpl at = ( AttributeTypeImpl ) object;
            attributeTypesList.remove( at );
            String[] names = at.getNamesRef();
            if ( names != null )
            {
                for ( String name : names )
                {
                    attributeTypesMap.remove( name.toLowerCase() );
                }
            }
            attributeTypesMap.remove( at.getOid() );
        }
        else if ( object instanceof MatchingRuleImpl )
        {
            MatchingRuleImpl mr = ( MatchingRuleImpl ) object;
            matchingRulesList.remove( mr );
            String[] names = mr.getNamesRef();
            if ( names != null )
            {
                for ( String name : names )
                {
                    matchingRulesMap.remove( name.toLowerCase() );
                }
            }
            matchingRulesMap.remove( mr.getOid() );
        }
        else if ( object instanceof ObjectClassImpl )
        {
            ObjectClassImpl oc = ( ObjectClassImpl ) object;
            objectClassesList.remove( oc );
            String[] names = oc.getNamesRef();
            if ( names != null )
            {
                for ( String name : names )
                {
                    objectClassesMap.remove( name.toLowerCase() );
                }
            }
            objectClassesMap.remove( oc.getOid() );
        }
        else if ( object instanceof SyntaxImpl )
        {
            SyntaxImpl syntax = ( SyntaxImpl ) object;
            syntaxesList.remove( syntax );
            String[] names = syntax.getNamesRef();
            if ( names != null )
            {
                for ( String name : names )
                {
                    syntaxesMap.remove( name.toLowerCase() );
                }
            }
            syntaxesMap.remove( syntax.getOid() );
        }
    }


    /**
     * Adds the given attribute type.
     *
     * @param at
     *      the attribute type
     */
    public void addAttributeType( AttributeTypeImpl at )
    {
        Schema schema = getSchema( at.getSchema() );

        if ( schema == null )
        {
            // TODO Throw an exception
        }

        schema.addAttributeType( at );
        addSchemaObject( at );

        // Notifying the listeners
        notifyAttributeTypeAdded( at );
    }


    /**
     * Update the source attribute type with the values of the 
     * destination attribute type.
     *
     * @param at1
     *      the source attribute type
     * @param at2
     *      the destination attribute type
     */
    public void modifyAttributeType( AttributeTypeImpl at1, AttributeTypeImpl at2 )
    {
        // Removing the references (in case of the names or oid have changed)
        removeSchemaObject( at1 );

        // Updating the attribute type
        at1.setNames( at2.getNamesRef() );
        at1.setOid( at2.getOid() );
        at1.setDescription( at2.getDescription() );
        at1.setSuperiorName( at2.getSuperiorName() );
        at1.setUsage( at2.getUsage() );
        at1.setSyntaxOid( at2.getSyntaxOid() );
        at1.setLength( at2.getLength() );
        at1.setObsolete( at2.isObsolete() );
        at1.setSingleValue( at2.isSingleValue() );
        at1.setCollective( at2.isCollective() );
        at1.setCanUserModify( at2.isCanUserModify() );
        at1.setEqualityName( at2.getEqualityName() );
        at1.setOrderingName( at2.getOrderingName() );
        at1.setSubstrName( at2.getSubstrName() );

        // Adding the references (in case of the names or oid have changed)
        addSchemaObject( at1 );

        // Notifying the listeners
        notifyAttributeTypeModified( at1 );
    }


    /**
     * Removes the given attribute type.
     *
     * @param at
     *      the attribute type
     */
    public void removeAttributeType( AttributeTypeImpl at )
    {
        Schema schema = getSchema( at.getSchema() );

        if ( schema == null )
        {
            // TODO Throw an exception
        }

        schema.removeAttributeType( at );
        removeSchemaObject( at );

        // Notifying the listeners
        notifyAttributeTypeRemoved( at );
    }


    /**
     * Adds the given object class.
     *
     * @param oc
     *      the object class
     */
    public void addObjectClass( ObjectClassImpl oc )
    {
        Schema schema = getSchema( oc.getSchema() );

        if ( schema == null )
        {
            // TODO Throw an exception
        }

        schema.addObjectClass( oc );
        addSchemaObject( oc );

        // Notifying the listeners
        notifyObjectClassAdded( oc );
    }


    /**
     * Update the source object class with the values of the 
     * destination object class.
     *
     * @param oc1
     *      the source object class
     * @param oc2
     *      the destination object class  
     */
    public void modifyObjectClass( ObjectClassImpl oc1, ObjectClassImpl oc2 )
    {
        // Removing the references (in case of the names or oid have changed)
        removeSchemaObject( oc1 );

        // Updating the object class
        oc1.setNames( oc2.getNamesRef() );
        oc1.setOid( oc2.getOid() );
        oc1.setDescription( oc2.getDescription() );
        oc1.setSuperClassesNames( oc2.getSuperClassesNames() );
        oc1.setType( oc2.getType() );
        oc1.setObsolete( oc2.isObsolete() );
        oc1.setMustNamesList( oc2.getMustNamesList() );
        oc1.setMayNamesList( oc2.getMayNamesList() );

        // Adding the references (in case of the names or oid have changed)
        addSchemaObject( oc1 );

        // Notifying the listeners
        notifyObjectClassModified( oc1 );
    }


    /**
     * Removes the given object class.
     *
     * @param oc
     *      the object class
     */
    public void removeObjectClass( ObjectClassImpl oc )
    {
        Schema schema = getSchema( oc.getSchema() );

        if ( schema == null )
        {
            // TODO Throw an exception
        }

        schema.removeObjectClass( oc );
        removeSchemaObject( oc );

        notifyObjectClassRemoved( oc );
    }


    /**
     * Notifies the SchemaHandler listeners that a schema has been added.
     *
     * @param schema
     *      the added schema
     */
    private void notifySchemaAdded( Schema schema )
    {
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.schemaAdded( schema );
        }
    }


    /**
     * Notifies the given listeners that a schema has been removed.
     *
     * @param schema
     *      the added schema
     */
    private void notifySchemaRemoved( Schema schema )
    {
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.schemaRemoved( schema );
        }
    }


    /**
     * Notifies the SchemaHandler listeners that an attribute type has been added.
     *
     * @param at
     *      the added attribute type
     */
    private void notifyAttributeTypeAdded( AttributeTypeImpl at )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.attributeTypeAdded( at );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( at.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).attributeTypeAdded( at );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that an attribute type has been modified.
     *
     * @param at
     *      the modified attribute type
     */
    private void notifyAttributeTypeModified( AttributeTypeImpl at )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.attributeTypeModified( at );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( at.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).attributeTypeModified( at );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that an attribute type has been removed.
     *
     * @param at
     *      the removed attribute type
     */
    private void notifyAttributeTypeRemoved( AttributeTypeImpl at )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.attributeTypeRemoved( at );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( at.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).attributeTypeRemoved( at );
            }
        }

        // Attribute Type Listeners
        List<?> atListeners = ( List<?> ) attributeTypeListeners.get( at );
        if ( atListeners != null )
        {
            for ( Object object : atListeners.toArray() )
            {
                ( ( AttributeTypeListener ) object ).attributeTypeRemoved();
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that an object class has been added.
     *
     * @param oc
     *      the added object class
     */
    private void notifyObjectClassAdded( ObjectClassImpl oc )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.objectClassAdded( oc );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( oc.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).objectClassAdded( oc );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that an object class has been modified.
     *
     * @param oc
     *      the modified object class
     */
    private void notifyObjectClassModified( ObjectClassImpl oc )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.objectClassModified( oc );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( oc.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).objectClassModified( oc );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that an object class has been removed.
     *
     * @param oc
     *      the removed object class
     */
    private void notifyObjectClassRemoved( ObjectClassImpl oc )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.objectClassRemoved( oc );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( oc.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).objectClassModified( oc );
            }
        }

        // Object Class Listeners
        List<?> ocListeners = ( List<?> ) objectClassListeners.get( oc );
        if ( ocListeners != null )
        {
            for ( Object object : ocListeners.toArray() )
            {
                ( ( ObjectClassListener ) object ).objectClassRemoved();
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that a matching rule has been added.
     *
     * @param mr
     *      the added matching rule
     */
    private void notifyMatchingRuleAdded( MatchingRuleImpl mr )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.matchingRuleAdded( mr );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( mr.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).matchingRuleAdded( mr );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that a matching rule has been modified.
     *
     * @param mr
     *      the modified matching rule
     */
    private void notifyMatchingRuleModified( MatchingRuleImpl mr )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.matchingRuleModified( mr );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( mr.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).matchingRuleModified( mr );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that a matching rule has been removed.
     *
     * @param mr
     *      the removed matching rule
     */
    private void notifyMatchingRuleRemoved( MatchingRuleImpl mr )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.matchingRuleRemoved( mr );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( mr.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).matchingRuleRemoved( mr );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that a syntax has been added.
     *
     * @param syntax
     *      the added syntax
     */
    private void notifySyntaxRuleAdded( SyntaxImpl syntax )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.syntaxAdded( syntax );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( syntax.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).syntaxAdded( syntax );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that a syntax has been modified.
     *
     * @param syntax
     *      the modified syntax
     */
    private void notifySyntaxRuleModified( SyntaxImpl syntax )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.syntaxModified( syntax );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( syntax.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).syntaxModified( syntax );
            }
        }
    }


    /**
     * Notifies the SchemaHandler listeners that a syntax has been removed.
     *
     * @param syntax
     *      the removed syntax
     */
    private void notifySyntaxRemoved( SyntaxImpl syntax )
    {
        // SchemaHandler Listeners
        for ( SchemaHandlerListener listener : schemaHandlerListeners.toArray( new SchemaHandlerListener[0] ) )
        {
            listener.syntaxRemoved( syntax );
        }

        // Schema Listeners
        List<?> listeners = ( List<?> ) schemaListeners.get( getSchema( syntax.getSchema() ) );
        if ( listeners != null )
        {
            for ( Object object : listeners.toArray() )
            {
                ( ( SchemaListener ) object ).syntaxRemoved( syntax );
            }
        }
    }


    /**
     * Verifies if the given alias or oid is already taken by a schema object
     *
     * @param id
     *      the alias or oid
     * @return
     *      true if the the alias or oid is already taken
     */
    public boolean isAliasOrOidAlreadyTaken( String id )
    {
        String lowerCasedId = id.toLowerCase();
        if ( attributeTypesMap.containsKey( lowerCasedId ) )
        {
            return true;
        }
        else if ( objectClassesMap.containsKey( lowerCasedId ) )
        {
            return true;
        }
        else if ( matchingRulesMap.containsKey( lowerCasedId ) )
        {
            return true;
        }
        else if ( syntaxesMap.containsKey( lowerCasedId ) )
        {
            return true;
        }

        return false;
    }


    /**
     * Verifies if the given name for a schema is already taken by another schema.
     *
     * @param name
     *      the name
     * @return
     *      true if the the name is already taken
     */
    public boolean isSchemaNameAlreadyTaken( String name )
    {
        return schemasMap.containsKey( name.toLowerCase() );
    }
}
