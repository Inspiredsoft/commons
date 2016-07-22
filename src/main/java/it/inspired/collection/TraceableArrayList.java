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

package it.inspired.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class extends the {@link ArrayList} to implements a traceble collection.
 * 
 * @author Massimo Romano
 *
 * @param <E>
 */
public class TraceableArrayList<E> extends ArrayList<E> implements TraceableCollection<E> {
	
	private static final long serialVersionUID = 6620134210723909766L;

	/*
	 * The deleted elements
	 */
	protected Collection<E> deletes;
    
	/*
	 * Default constructor
	 */
    public TraceableArrayList() {
        super();
        deletes = new ArrayList<E>();
    }

    /*
     * Constructor from {@link List}
     */
    public TraceableArrayList( List<E> list ) {
        super( list );
    }

    /*
     * (non-Javadoc)
     * @see java.util.ArrayList#remove(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
	public boolean remove( Object obj ) {
    	if ( super.contains( obj ) ) {
        	deletes.add( (E) obj );
        }        
        return super.remove( obj );
    }
    
    /*
     * (non-Javadoc)
     * @see java.util.ArrayList#remove(int)
     */
    @SuppressWarnings("unchecked")
	public E remove(int index) {
        Object obj = get( index );
        if ( obj != null ) {
        	deletes.add( (E) obj );
        }        
        return super.remove( index );
    }
        
    /*
     * (non-Javadoc)
     * @see java.util.ArrayList#clear()
     */
	@SuppressWarnings("unchecked")
	public void clear() {
		Iterator<E> iter = iterator();
		while (iter.hasNext()) {
			Object currentObj = iter.next();
	        if ( currentObj != null) {
	        	deletes.add( (E) currentObj );
	        }			
		}
		super.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see it.inspired.collection.TraceableCollection#getDeletes()
	 */
	public Collection<E> getDeletes(){
        return deletes;
    }

	/*
	 * (non-Javadoc)
	 * @see it.inspired.collection.TraceableCollection#setDeletes(java.util.Collection)
	 */
    public void setDeletes( Collection<E> deletes ) {
        this.deletes = deletes;
    }
}
