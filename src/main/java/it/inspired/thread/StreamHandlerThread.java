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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/**
 * This class can read a stream asynchronously.
 * 
 * @author Massimo Romano
 *
 */
public class StreamHandlerThread implements Callable<String>
{	
	InputStream 	is = null;
	BufferedReader 	br = null;
	String output = "";
	
	/**
	 * Default constructor.
	 * 
	 * @param is The stream to read.
	 */
	public StreamHandlerThread(InputStream is) {
		super();
		this.is = is;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public String call() throws Exception
	{
		String line = "";
		br = new BufferedReader( new InputStreamReader( is ) );
		
     	try {
			while( ( line = br.readLine() ) != null ) 
			{
				output += line + System.getProperty("line.separator");
			}
		} 
     	finally
     	{
     		 if (is != null)
 		        try { is.close(); } catch (IOException e) {}
		}
		return output;
	}

	

}
