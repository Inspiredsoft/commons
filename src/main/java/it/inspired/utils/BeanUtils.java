/*******************************************************************************
* Inspired commons is a set of utility classes.
* Copyright (C) 2016 Inspired Soft
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.    
*******************************************************************************/

package it.inspired.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Massimo Romano
 *
 */
public class BeanUtils {
	
	@SuppressWarnings("rawtypes")
	public static boolean hasProperty( Class clazz, String propertyName )
	{
		try 
		{
			new PropertyDescriptor( propertyName, clazz );
		}
		catch (IntrospectionException e) 
		{
			return false;
		}
		return true;
	}
	
	//--------------------------------------------------------------------------------------
	
	@SuppressWarnings("rawtypes")
	public static PropertyDescriptor getPropertyDescriptor( Class clazz, String propertyName )
	{
		try 
		{
			return new PropertyDescriptor( propertyName, clazz );
		}
		catch (IntrospectionException e) 
		{
			try 
			{
				return new PropertyDescriptor( propertyName, clazz, getPropertyGetterName( propertyName ), null  );
			} 
			catch (IntrospectionException e1) 
			{
				return null;
			}
		}
	}
	
	//--------------------------------------------------------------------------------------
	
	@SuppressWarnings("rawtypes")
	public static boolean isPrimitive( PropertyDescriptor property )
	{
		Class type = property.getPropertyType();
		return type.isPrimitive() || isPrimitive( type );
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isPrimitive( Class type )
	{
		return ( type == Integer.class 	 || type == Double.class  || type == String.class     || 
  				 type == Date.class 	 || type == Boolean.class || type == BigDecimal.class ||
  				 type == Timestamp.class || type == Long.class );
	}
	
	//--------------------------------------------------------------------------------------

	/**
     * Retrieve a value from a property using
     * 
     * @param obj The object who's property you want to fetch
     * @param property The property name
     * @param defaultValue A default value to be returned if either a) The property is
     *  not found or b) if the property is found but the value is null
     * @return THe value of the property
     */
    @SuppressWarnings("unchecked")
	public static <T> T getProperty(Object obj, String property, T defaultValue) {

        T returnValue = (T) getProperty(obj, property);
        if (returnValue == null) {
            returnValue = defaultValue;
        }

        return returnValue;

    }

    /**
     * Fetch a property from an object. For example of you wanted to get the foo
     * property on a bar object you would normally call {@code bar.getFoo()}. This
     * method lets you call it like {@code BeanUtil.getProperty(bar, "foo")}
     * @param obj The object who's property you want to fetch
     * @param property The property name
     * @return The value of the property or null if it does not exist.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getProperty(Object obj, String property) {
        Object returnValue = null;

        try {
            String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
            Class clazz = obj.getClass();
            Method method = clazz.getMethod(methodName, null);
            returnValue = method.invoke(obj, null);
        }
        catch (Exception e) {
            // Do nothing, we'll return the default value
        }

        return returnValue;
    }
    
    public static String capitalizeMethodName( String text )
	{
		String res = "";
		if ( !StringUtils.isEmpty( text ) )
		{
			for ( int i = 0; i<text.length(); i++ )
			{
				Character c = text.charAt( i );
				if ( i == 0 )
				{
					res += Character.toUpperCase( c );
				}
				else if ( Character.isUpperCase(c) )
				{
					res += " " + c;
				}
				else
				{
					res += c;
				}
			}
		}
		return res;
	}
    
    //------------------------------------------------------------------------------------
    
    public static String getPropertyGetterName( String property ) {
    	return "get" + property.charAt( 0 ) + property.substring( 1 );
    }
    
    //------------------------------------------------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<?> mergeRemovingDuplicates( List<?> l1, List<?> l2 )
	{
		List res = new ArrayList(l1);
		res.removeAll( l2 );
		res.addAll( l2 );
		return res;
	}
	
	 //------------------------------------------------------------------------------------
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty( Object obj )
	{
		boolean result = false;
		if ( obj == null )
		{
			result = true;
		}
		else if ( obj instanceof String )
		{
			result = StringUtils.isEmpty( obj.toString() );
		}
		else if ( obj instanceof List )
		{
			result = ((List) obj).isEmpty();
		}
		return result;
	}
}
