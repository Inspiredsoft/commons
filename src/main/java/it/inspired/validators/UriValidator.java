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

package it.inspired.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for URI.
 * 
 * @author Massimo Romano
 *
 */
public class UriValidator {

	private static final String EMAIL_PATTERN = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
	
	private static final String IPADDRESS_PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	private static Pattern emailPattern;
	private static Pattern ipPattern;
	
	static 
	{
		emailPattern 	= Pattern.compile(EMAIL_PATTERN);
		ipPattern 		= Pattern.compile(IPADDRESS_PATTERN);
	}
	
	public static boolean isValidIp( String ip ) 
	{
		Matcher matcher = ipPattern.matcher( ip );
		return matcher.matches();
	}
	
	public static boolean isValidEmail( String email )
	{
		Matcher matcher = emailPattern.matcher( email );
		return matcher.matches();
	}
	
}
