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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Utility class to manage date.
 * 
 * @author Massimo Romano
 *
 */
public class DateUtils
{
	private static final long MILLISECONDS_PER_DAY = 86400000L;
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the year part of the given date
	 * @param date
	 * @return
	 */
	public static Integer getYear( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		return cal.get( Calendar.YEAR );
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the month part of the given date
	 * @param date
	 * @return
	 */
	public static Integer getMonth( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		return cal.get( Calendar.MONTH );
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the day of the month part of the given date
	 * @param date
	 * @return
	 */
	public static Integer getDayOfMonth( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		return cal.get( Calendar.DAY_OF_MONTH );
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the hour part of the given date
	 * @param date
	 * @return
	 */
	public static Integer getHour( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		return cal.get( Calendar.HOUR_OF_DAY );
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the minute part of the given date
	 * @param date
	 * @return
	 */
	public static Integer getMinute( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		return cal.get( Calendar.MINUTE );
	}
	
	//------------------------------------------------------------------------------------------

	/**
	 * Return the day before the given date
	 * @param date
	 * @return
	 */
	public static Date yesterday( Date date )
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( date );
		calendar.add( Calendar.DATE, -1 );
		return calendar.getTime();
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the day after the given date
	 * @param date
	 * @return
	 */
	public static Date tomorrow( Date date )
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( date );
		calendar.add( Calendar.DATE, 1 );
		return calendar.getTime();
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the given date to the midday hour
	 * @param date
	 * @return
	 */
	public static Date toMidday(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( date );
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the given date to the midnight hour
	 * @param date
	 * @return
	 */
	public static Date toMidnight(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( date );
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the given date to the morning hour
	 * @param date
	 * @return
	 */
	public static Date toMorning(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( date );
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the number of days between two dates
	 * @param start
	 * @param end
	 * @return
	 */
	public static long days( Date start, Date end )
	{
		long days = 0;
		if ( start.compareTo( end ) < 0 )
		{
			days = TimeUnit.DAYS.convert( end.getTime()-start.getTime(), TimeUnit.MILLISECONDS );
		}
		return days;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the number of seconds between two dates
	 * @param start
	 * @param end
	 * @return
	 */
	public static long seconds( Date start, Date end )
	{
		long days = 0;
		if ( start.compareTo( end ) < 0 )
		{
			days = TimeUnit.SECONDS.convert( end.getTime()-start.getTime(), TimeUnit.MILLISECONDS );
		}
		return days;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the number of months between two dates
	 * @param start
	 * @param end
	 * @return
	 */
	public static int months( Date start, Date end )
	{
		int months = 0;
		if ( start.compareTo( end ) < 0 )
		{
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime( end );
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime( start );
			
			int yearDiff = cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR);
			months = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH) + 1 + 12 * yearDiff;
		}
		return months;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the number of hours between two dates
	 * @param start
	 * @param end
	 * @return
	 */
	public static long hours( Date start, Date end )
	{
		long days = 0;
		if ( start.compareTo( end ) < 0 )
		{
			days = TimeUnit.HOURS.convert( end.getTime()-start.getTime(), TimeUnit.MILLISECONDS );
		}
		return days;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the number of minutes between two dates
	 * @param start
	 * @param end
	 * @return
	 */
	public static long minutes( Date start, Date end ) {
		long minutes = 0;
		if ( start.compareTo( end ) < 0 )
		{
			minutes = TimeUnit.MINUTES.convert( end.getTime()-start.getTime(), TimeUnit.MILLISECONDS );
		}
		return minutes;
	}
	
	//------------------------------------------------------------------------------------------

	/**
	 * Add the given number of days to the date
	 * @param date
	 * @return
	 */
	public static Date addDay(Date date, int days)
	{
		Calendar ci = Calendar.getInstance();
		ci.setTime( date );
		ci.add( Calendar.DAY_OF_MONTH, days );
		
		return ci.getTime();
	}
	
	//------------------------------------------------------------------------------------------

	/**
	 * Add the given number of months to the date
	 * @param date
	 * @return
	 */
	public static Date addMonth(Date date, int months)
	{
		Calendar ci = Calendar.getInstance();
		ci.setTime( date );
		ci.add( Calendar.MONTH, months );
		
		return ci.getTime();
	}
	
	//------------------------------------------------------------------------------------------

	/**
	 * Add the given number of hours to the date
	 * @param date
	 * @return
	 */
	public static Date addHour(Date date, int hours)
	{
		Calendar ci = Calendar.getInstance();
		ci.setTime( date );
		ci.add( Calendar.HOUR, hours );
		
		return ci.getTime();
	}
	
	//------------------------------------------------------------------------------------------

	/**
	 * Add the given number of minutes to the date
	 * @param date
	 * @return
	 */
	public static Date addMin(Date date, int min)
	{
		Calendar ci = Calendar.getInstance();
		ci.setTime( date );
		ci.add( Calendar.MINUTE, min );
		
		return ci.getTime();
	}
	
	//------------------------------------------------------------------------------------------

	/**
	 * Add the given number of seconds to the date
	 * @param date
	 * @return
	 */
	public static Date addSec(Date date, int min)
	{
		Calendar ci = Calendar.getInstance();
		ci.setTime( date );
		ci.add( Calendar.SECOND, min );
		
		return ci.getTime();
	}

	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the number of day between now and the given date
	 * @param date
	 * @return
	 */
	public static Long daysAgo( Date date ) 
	{
		Date today = new Date();
		long dif = today.getTime() - date.getTime();
		return dif / MILLISECONDS_PER_DAY;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Convert the number of days in milliseconds
	 * @param day
	 * @return
	 */
	public static long daysToMilli( long days )
	{
		return days * MILLISECONDS_PER_DAY;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Check if two dates are the same
	 * @param cal1
	 * @param cal2
	 * @return
	 */
	public static boolean areSameDay(Calendar cal1, Calendar cal2) 
	{
		if (cal1 == null || cal2 == null) 
	    {
			return false;
	    }
	    return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
	    		cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Check if two dates are the same
	 * @param cal1
	 * @param cal2
	 * @return
	 */
	public static boolean areSameDay(Date date1, Date date2) 
	{
        if (date1 == null || date2 == null) 
        {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return areSameDay(cal1, cal2);
    }
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Check if the date is today
	 * @param date
	 * @return
	 */
	public static boolean isToday( Date date )
	{
		if ( date == null )
		{
			return false;
		}
		return areSameDay(date, Calendar.getInstance().getTime());
		
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Parse the string date using the formatter
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parse( String date, String format ) throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat( format );
		return (Date) formatter.parse( date );
	}

	//------------------------------------------------------------------------------------------
	
	/**
	 * Format the date using the formatter
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format( Date date, String format )
	{
		DateFormat formatter = new SimpleDateFormat( format );
		return formatter.format( date );
	}
		
	//------------------------------------------------------------------------------------------
	
	/**
	 * Get teh today year
	 * @return
	 */
	public static int todayYear()
	{
		return Calendar.getInstance().get( Calendar.YEAR );
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Get teh today month
	 * @return
	 */
	public static int todayMonth()
	{
		return Calendar.getInstance().get( Calendar.MONTH );
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the minimum between two date
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date min( Date date1, Date date2 )
	{
		if ( date1.after( date2 ) )
		{
			return date2;
		}
		return date1;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the maximum between two date
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date max( Date date1, Date date2 )
	{
		if ( date1.after( date2 ) )
		{
			return date1;
		}
		return date2;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Check if a date is in the given range of dates
	 * @param date
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean inRange( Date date, Date from, Date to )
	{
		if ( from.before( to ) || from.equals( to ) )
		{
			return !( date.before( from ) || date.after( to ) );
		}
		return false;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the first day of the month for the given date
	 * @param date
	 * @return
	 */
	public static Date firstDayOfMoth(Date date)
	{
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set( Calendar.DAY_OF_MONTH, 1 );
        return cal.getTime();
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Return the last day of the month for the given date
	 * @param date
	 * @return
	 */
	public static Date lastDayOfMoth(Date date)
	{
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set( Calendar.DAY_OF_MONTH, maxDay );
        return cal.getTime();
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Create a date from year, month and day
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date create( Integer year, Integer month, Integer day )
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
}
