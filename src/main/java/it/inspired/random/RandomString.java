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

package it.inspired.random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * This abstract class can be extended to have a random string generato for different alfabet.
 * 
 * @author Massimo Romano
 *
 */
public abstract class RandomString {
    
    private boolean secure = false;
    
    //-------------------------------------------------------------------------------
    
    /**
     * Return the alphabet allowed to generate the random string.
     * 
     * @return The alphabet.
     */
    public abstract String getAlphabet();
    
    //-------------------------------------------------------------------------------
    
    /**
     * Return the length of the alphabet.
     * @return Length of the alphabet. 
     */
    public Integer getLenght()
    {
    	return getAlphabet().length();
    }
    
    //-------------------------------------------------------------------------------
    
    /**
     * Return true if a {@link SecureRandom} class is used to generate the random string.
     * 
     * @return True if a {@link SecureRandom} class is used.
     */
    public boolean isSecure()
    {
    	return this.secure;
    }
    
    /**
     * Setting this property is possible to change the class used to generate the random string 
     * from {@link Random} to {@link SecureRandom}
     * 
     * @param secure If true the generator is more secure.
     */
    public void setSecure( boolean secure )
    {
    	this.secure = secure;
    }
 
    //-------------------------------------------------------------------------------
    
    /**
     * Generate a random string.
     * 
     * @return The string generated.
     */
    public String generate() {
    	
    	Random rnd = getRandom();
    	
        StringBuilder sb = new StringBuilder( getLenght() );
        for (int i = 0; i < getLenght(); i++)
        {
            sb.append( getAlphabet().charAt( rnd.nextInt( getAlphabet().length() ) ) );
        }
        
        return sb.toString();
    }
    
    //-------------------------------------------------------------------------------
    // Private methods
    //-------------------------------------------------------------------------------
    
    private Random getRandom()
    {
    	Random rnd;
    	if ( secure ) 
    	{
    		try 
    		{
				rnd = SecureRandom.getInstance("SHA1PRNG");
			} 
    		catch (NoSuchAlgorithmException e) 
    		{
				throw new RuntimeException( e.getMessage() );
			}
    	}
    	else
    	{
    		rnd = new Random( System.currentTimeMillis() );
    	}
    	return rnd;
    }
    
}
