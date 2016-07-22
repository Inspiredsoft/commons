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

package it.inspired.reflection;

/**
 * 
 * @author Massimo Romano
 *
 */
public class NewInstanceException extends RuntimeException {

	private static final long serialVersionUID = 5571507296916808525L;

	@SuppressWarnings("rawtypes")
	public NewInstanceException( Class clazz, Throwable cause ) {
		super( "Error instantiating class " + ( clazz != null ? clazz.getName() : "<null class>" ), cause);
	}
	public NewInstanceException( String clazz, Throwable cause ) {
		super( "Error instantiating class " + ( clazz != null ? clazz : "<null class>" ), cause);
	}

}
