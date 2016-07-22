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

package it.inspired.thread;

import java.util.ArrayList;
import java.util.List;

import it.inspired.utils.OSUtils;
import it.inspired.utils.StringUtils;

/**
 * This class executes 3 ping and can be used both in Windows and Linux environment.
 * 
 * @author Massimo Romano
 *
 */
public class Ping extends CommandCaller {
	
	private String host;
	
	//---------------------------------------------------------------
	
	public Ping( String host ) {
		this.host = host;
	}
	
	//---------------------------------------------------------------
	
	public String send() {
		return send(null);
	}
	
	public String send( String separator) {
		List<String> result = new ArrayList<String>();
		
		String command = "";
		if ( OSUtils.isWindows() ) {
			command = "ping -n 3";
		} else {
			command = "ping -c 3";
		}
		
		try {
			super.exec("/", command + " " + host );
		} 
		catch (Exception e) 
		{
			if ( getExitCode() != 0 ) {
				result.add( e.getMessage() );
			}
		}
		
		if ( getExitCode() != 0 ) {
			result.add( "Exit code: " + getExitCode() );
		}
		if ( !StringUtils.isEmpty( getOutput() ) ) 
		{
			result.add( getOutput() );
		}
		if ( !StringUtils.isEmpty( getError() ) ) 
		{
			result.add(  getError() );
		}
		
		return org.apache.commons.lang3.StringUtils.join( result.toArray(), separator);
	}

	//---------------------------------------------------------------
	
	public static void test(String[] args) {
		Ping ping = new Ping("10.51.0.51");
		System.out.println( ping.send("\n") );
	}

}
