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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Massimo Romano
 *
 */
public class ListUtils
{
	/**
	 * Merge two list removing duplicates
	 * @param l1 First list.
	 * @param l2 Second list.
	 * @return Merged list.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<?> mergeRemovingDuplicates( List<?> l1, List<?> l2 )
	{
		List res = new ArrayList(l1);
		res.removeAll( l2 );
		res.addAll( l2 );
		return res;
	}

}
