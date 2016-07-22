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

import java.util.Date;

/**
 * This class is an implementation of a chronometer.
 * 
 * @author Massimo Romano
 *
 */
public class Chrono 
{
	private Date start;
	private Date lap;
	private Date end;

	public Chrono()
	{
		this.start = new Date();
	}
	
	/**
	 * Reset the chronometer.
	 */
	public void reset()
	{
		this.start = new Date();
	}
	
	/**
	 * Start the chronometer.
	 */
	public void start()
	{
		this.start = new Date();
	}
	
	/**
	 * Stop the chronometer.
	 */
	public void stop()
	{
		this.end = new Date();
	}
	
	/**
	 * Get a lap. Just one lap is permitted.
	 * 
	 * @return The execution from previous lap or start.
	 */
	public Long lap()
	{
		Date lap = new Date();
		Long elapsed = lap.getTime() - ( this.lap != null ? lap.getTime() : start.getTime() );
		this.lap = lap;
		return elapsed;
	}
	
	/**
	 * Return the execution time from start to end.
	 * @return
	 */
	public Long elapsed()
	{
		if ( end != null )
		{
			return end.getTime() - start.getTime();
		}
		else
		{
			return ( new Date() ).getTime() - start.getTime();
		}
	}
	
	/**
	 * Return the start date.
	 * @return Te start date.
	 */
	public Date getStart()
	{
		return this.start;
	}
	
	/**
	 * Return the end date.
	 * @return The end date.
	 */
	public Date getEnd()
	{
		return this.end;
	}
	
	/**
	 * return a started chronometer.
	 * @return The started chronometer.
	 */
	public static Chrono newStart()
	{
		Chrono chrono = new Chrono();
		chrono.start();
		return chrono;
	}
}
