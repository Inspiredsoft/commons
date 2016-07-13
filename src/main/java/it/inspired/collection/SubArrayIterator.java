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

package it.inspired.collection;

import java.util.Iterator;

import org.apache.commons.lang3.ArrayUtils;

/**
 * This class implements an {@link Iterator} over an array
 * that generate a subset on each iteration.
 * 
 * 
 * @author Massimo Romano
 *
 */
@SuppressWarnings("rawtypes")
public class SubArrayIterator implements Iterator {

	private Object[] array = null;
	private int len = 0;

	private int index = 0;
	private int idStart = 0;
	private int idEnd = 0;

	/**
	 * Constructor for the iterator
	 * 
	 * @param array Arrey to iterate
	 * @param len The length of the sub array
	 */
	public SubArrayIterator(Object[] array, int len) {
		this.array = array;
		this.len = len;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return len > 0 && array != null && array.length > 0
				&& (idEnd != (array.length));
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public Object next() {
		idStart = index * len;
		idEnd = Math.min(array.length, index * len + len); // end index e'
															// escluviso

		index++;

		return ArrayUtils.subarray(array, idStart, idEnd);
	}

	/*
	 * Not implemented
	 * 
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new RuntimeException("Method not implemented");
	}

}
