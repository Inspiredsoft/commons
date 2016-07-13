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

import java.util.Collection;

/**
 * This interface are implemented by a collection to trace the element removed
 * 
 * @author Massimo Romano
 *
 * @param <E>
 */
public interface TraceableCollection<E> extends Collection<E> {    
    
	/**
	 * Returns the collection of removed elements
	 * 
	 * @return The removed elements
	 */
    public Collection<E> getDeletes();
    
    /**
     * Set the collection of removed elements
     * 
     * @param deletes The removed elements to set
     */
    public void setDeletes( Collection<E> deletes );
    
}
