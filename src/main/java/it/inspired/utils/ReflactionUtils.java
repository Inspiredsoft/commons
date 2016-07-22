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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Utility class for reflection.
 * 
 * @author Massimo Romano
 *
 */
public class ReflactionUtils 
{	
	/**
	 * Set null to all accessible field.
	 * @param object The object to clear.
	 */
	public static void clearAccessibleField( Object object )
	{
		Field[] fields = object.getClass().getDeclaredFields();
		for ( Field field : fields )
		{
			if ( PropertyUtils.isWriteable( object, field.getName() ) )
			{
				try 
				{
					PropertyUtils.setSimpleProperty( object, field.getName(), null );
				} 
				catch (IllegalAccessException e) {} 
				catch (InvocationTargetException e){} 
				catch (NoSuchMethodException e) {}
			}
		}
	}

}
