package org.apache.directory.studio.openldap.common.ui.model;

/**
 * The list of Password Hashes choices
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public enum PasswordHashEnum
{
    NO_CHOICE( 0,"" ),
    CLEARTEXT( 1, "{CLEARTEXT}" ),
    CRYPT( 2, "{CRYPT}" ),
    LANMAN( 3, "{LANMAN}" ),
    MD5( 4, "{MD5}" ),
    SMD5( 5, "{SMD5}" ),
    SHA( 6, "{SHA}" ),
    SSHA( 7, "{SSHA}" ),
    UNIX( 8, "{UNIX}" );
    
    /** The hash number */
    private int number;
    
    /** The interned name */
    private String name;
    
    /**
     * Instanciation of the values
     */
    private PasswordHashEnum( int number, String name )
    {
        this.name = name;
        this.number = number;
    }
    
    
    /**
     * @return The Password Hash name
     */
    public String getName()
    {
        return name;
    }
    
    
    /**
     * Get the PasswordHashEnum instance from its number
     * 
     * @param number The number we are looking for
     * @return The associated PasswordHashEnum instance
     */
    public static PasswordHashEnum getPasswordHash( int number )
    {
        switch ( number )
        {
            case 1 : return CLEARTEXT;
            case 2 : return CRYPT;
            case 3 : return LANMAN;
            case 4 : return MD5;
            case 5 : return SMD5;
            case 6 : return SHA;
            case 7 : return SSHA;
            case 8 : return UNIX;
            default : return NO_CHOICE;
        }
    }
    
    
    /**
     * Get the PasswordHashEnum instance from its number
     * 
     * @param number The number we are looking for
     * @return The associated PasswordHashEnum instance
     */
    public static PasswordHashEnum getPasswordHash( String name )
    {
        if ( CLEARTEXT.name.equals( name ) )
        {
            return CLEARTEXT;
        }
        
        if ( CRYPT.name.equals( name ) )
        {
            return CRYPT;
        }

        if ( LANMAN.name.equals( name ) )
        {
            return LANMAN;
        }
        
        if ( MD5.name.equals( name ) )
        {
            return MD5;
        }
        
        if ( SMD5.name.equals( name ) )
        {
            return SMD5;
        }
        
        if ( SHA.name.equals( name ) )
        {
            return SHA;
        }
        
        if ( SSHA.name.equals( name ) )
        {
            return SSHA;
        }
        
        if ( UNIX.name.equals( name ) )
        {
            return UNIX;
        }
        
        return NO_CHOICE;
    }
}