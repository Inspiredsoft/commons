/*******************************************************************************
* Inspired Model Exporter is a framework to export data from pojo class.
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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Utility class for string.
 * 
 * @author Massimo Romano
 *
 */
public class StringUtils 
{
	/**
	 * Check if a string is null or empty
	 * @param str The string to check.
	 * @return True if null or empty.
	 */
	public static boolean isEmpty( String str )
	{
		return str == null || str.isEmpty();
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Generates a SHA-1 digest of the string
	 * @param text String to scramble.
	 * @return The scrambled string.
	 */
	public static String scramble(String text) 
	{
		try 
		{
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(text.getBytes("UTF-8"));
			return toHex(md.digest());
		} 
		catch (UnsupportedEncodingException e)
		{
			return null;
		} 
		catch (NoSuchAlgorithmException e)
		{
			return null;
		}
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Capitalize the source string (es. "action command" -> "Action command"
	 * @param text String to capitalize.
	 * @return Capitalized string.
	 */
	public static String capitalize( String text )
	{
		return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Capitalize all the words in the source string (es. "action command" -> "Action Command"
	 * @param text String to capitalize.
	 * @return Capitalized string.
	 */
	public static String capitalizeWord(String str)
	{
        String result = "";
        Scanner scn = new Scanner( str );
        while ( scn.hasNext() )
        {
            String next = scn.next();
            //make the first letter uppercase
            result += Character.toString(next.charAt(0)).toUpperCase();
            
            //do nothing with all the other letters
            for (int i = 1; i < next.length(); i++) 
            {
                result += Character.toString(next.charAt(i)); 
            }
            result += " "; 
        }
        scn.close();
        return result;
    }
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Capitalize all the words in the source method name (es. "actionCommand" -> "Action Command"
	 * @param name The method name.
	 * @return Capitalized string.
	 */	
	public static String capitalizeMethodName( String name )
	{
		String res = "";
		if ( !isEmpty( name ) )
		{
			for ( int i = 0; i<name.length(); i++ )
			{
				Character c = name.charAt( i );
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
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Add a character to the left of the source string until reached the total length required 
	 * @param text String to pad.
	 * @param padder Character to add.
	 * @param chars Length of the final string.
	 * @return
	 */
	public static String padLeft( String text, char padder, long chars )
	{
		long num = chars - text.length();
		if ( num > 0 )
		{
			for ( int i = 0; i < num; i++ )
			{
				text = padder + text;
			}
		}
		return text;
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Return the entire stack trace of the given exception
	 * @param e Exception.
	 * @return The full stack trace.
	 */
	public static String getStackTrace(Throwable e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace( new PrintWriter(sw) );

		return sw.toString();
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Cut the right part of the string to fit the required length
	 * @param str The string to cut.
	 * @param length The length required.
	 * @return The cutted string if greater than the required length or the original string.
	 */
	public static String cut( String str, int length )
	{
		String cutted = str;
		
		if ( str.length() > length )
		{
			cutted = str.substring( 0, length -1 );
		}
		
		return cutted;
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Cut the right part of the string since the first occurrence of the given character
	 * @param str The string.
	 * @param c The character.
	 * @return The cutted string.
	 */
	public static String cutTo( String str, char c )
	{
		int to = str.indexOf( c );
		if ( to > 0 )
		{
			return str.substring(0, to );
		}
		return str;
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Format the given seconds in HH/MM/SS
	 * 
	 * @param seconds
	 * @return
	 */
	public static String secToHHMMSS( Integer seconds )
	{
		long ore = seconds / 3600;
	    long tmp = seconds - 3600 * ore;
	    long min = tmp / 60;
	    long sec = tmp - 60 * min;

	    String hh = padLeft( Long.toString(ore), '0', 2 );
	    String mm = padLeft( Long.toString(min), '0', 2 );
	    String ss = padLeft( Long.toString(sec), '0', 2 );

	    return hh +":"+ mm +":"+ ss;
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Compare two string removing the initials 0
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean equalsIgnoreZeros( String value1, String value2 )
	{
		value1 = org.apache.commons.lang3.StringUtils.stripStart( value1, "0" );
		value2 = org.apache.commons.lang3.StringUtils.stripStart( value2, "0" );
		return value1 != null && value2 != null && value1.equals( value2 );
	}
	
	//---------------------------------------------------------------------------------
	/**
	 * Return a substring of fixed length from the position begin of a given string
	 * @param str
	 * @param begin
	 * @param length
	 * @return
	 */
	public static String subStringLength( String str, int begin, int length )
	{
		if ( begin >= 0 && length > 0 )
		{
			int end = begin + length;
			if ( end > str.length() )
			{
				end = str.length();
			}
			return str.substring( begin, end );
		}
		return "";
	}
	
	//---------------------------------------------------------------------------------
	
	/**
	 * Retunr a new String from the given str repeated repeat times
	 * @param str
	 * @param chars
	 * @return
	 */
	public static String build( String str, Long repeat )
	{
		StringBuilder builder = new StringBuilder();
		if ( repeat > 0 && !isEmpty( str ) ) 
		{
	        for(int i = 0; i < repeat; i++ )
	        {
	        	builder.append( str );
	        }
		}
        return builder.toString();
	}
	
	//---------------------------------------------------------------------------------
	
	public static String concatenate( String str1, String str2 ) 
	{
		if ( str1 != null && str2 != null) {
			return str1 + str2;
		} else if ( str1 != null ) {
			return str1;
		} else {
			return str2;
		}
	}
	
	//---------------------------------------------------------------------------------
	// PRIVATE METHODS
	//---------------------------------------------------------------------------------
	
	private static String toHex(byte[] data) 
	{
		StringBuffer sb = new StringBuffer();
		for (byte b : data)
		{
			String digit = Integer.toString(b & 0xFF, 16);

			if (digit.length() == 1)
			{
				sb.append("0");
			}
			sb.append(digit);
		}
		return sb.toString();
	}
}
