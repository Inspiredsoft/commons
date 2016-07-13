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

package it.inspired.thread;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class can execute shell command on the runtime where the application is deployed.
 * 
 * Example commands are: ls, mv, etc.
 * 
 * CommandCaller cc = new CommandCaller();
 * System.out.println( cc.exec( "/Home/test", "ls" ) );
 * System.out.println( "Exit code: " + cc.getExitCode() );
 * System.out.println( "Error: " + cc.getError() );
 * 
 * 
 * @author Massimo Romano
 *
 */
public class CommandCaller 
{
	private String output 	= "";
	private String error 	= "";
	private int exitCode	= 0;	
	
	/**
	 * return the output of the executed command.
	 * @return The oytput.
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * return the error of the executed command.
	 * @return The error.
	 */
	public String getError() {
		return error;
	}

	/**
	 * return the exit code of the executed command.
	 * @return The exit code.
	 */
	public int getExitCode() {
		return exitCode;
	}

	/**
	 * Execute the command.
	 * 
	 * @param path Path where to execute the command.
	 * @param command The command to execute.
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String exec( String path, String command ) throws IOException, InterruptedException 
	{
		this.output 	= "";
		this.error 		= "";
		this.exitCode	= 0;

		File dir = new File( path );
        Process process = Runtime.getRuntime().exec(command, null, dir);

        ExecutorService executor = Executors.newFixedThreadPool(2);      
        Future<String> outf = executor.submit( new StreamHandlerThread( process.getInputStream() ) );
        Future<String> errf = executor.submit( new StreamHandlerThread( process.getErrorStream() ) );   
        executor.shutdown();
        while (!executor.isTerminated()) {}
        
        try {
			this.error = errf.get();
			this.output = outf.get();
		} catch (ExecutionException e)
		{
			throw new RuntimeException( e );
		}
    
        exitCode = process.waitFor();
        
    	if (exitCode != 0) {
        	throw new RuntimeException( this.error );
    	}
    	
		return output;
	}
}
