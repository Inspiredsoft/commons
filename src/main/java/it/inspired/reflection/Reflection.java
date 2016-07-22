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

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class contains some useful reflection methods.
 * 
 * @author Massimo Romano
 *
 */
@SuppressWarnings("unchecked")
public class Reflection {
	
	private static final Log log = LogFactory.getLog( Reflection.class );

	/**
	 * Get property value from an object.
	 * 
	 * @param object The object.
	 * @param property The property name.
	 * @return The property value.
	 */
	public static Object getProperty( Object object, String property ) {
		try {
			return PropertyUtils.getProperty( object, property );
		} catch( IllegalAccessException ex ) {
			throw new InvalidPropertyException( property, ex );
		} catch( InvocationTargetException ex ) {
			throw new InvalidPropertyException( property, ex );
		} catch( NoSuchMethodException ex ) {
			throw new InvalidPropertyException( property, ex );
		}
	}
	
	/**
	 * Set property value in an object.
	 * 
	 * @param object The object.
	 * @param property The property to name.
	 * @param value The value to set.
	 */
	public static void setProperty( Object object, String property, Object value ) {
		try {
			PropertyUtils.setProperty( object, property, value );
		} catch( IllegalAccessException ex ) {
			throw new InvalidPropertyException( property, ex );
		} catch( InvocationTargetException ex ) {
			throw new InvalidPropertyException( property, ex );
		} catch( NoSuchMethodException ex ) {
			throw new InvalidPropertyException( property, ex );
		}
	}

	/**
	 * Set a property value in an object.
	 * 
	 * @param object The object.
	 * @param propName The property name to set.
	 * @param propValue The value to set.
	 */
	public static void setStringProperty(Object object, String propName, String propValue )  {
		if ( object!=null ){
			PropertyDescriptor propDescr = Reflection.getPropertyDescriptor( object, propName );
			String propClass = propDescr.getPropertyType().getName();
			
			if ( "int".equals( propClass )  || "java.lang.Integer".equals( propClass )  ){
					Reflection.setProperty( object, propName, new Integer( propValue ) );

			} else if ( "double".equals( propClass )  || "java.lang.Double".equals( propClass )  ){
					Reflection.setProperty( object, propName, new Double( propValue ) );
			
			} else if ( "float".equals( propClass )  || "java.lang.Float".equals( propClass )  ){
					Reflection.setProperty( object, propName, new Float( propValue ) );
			
			} else if ( "boolean".equals( propClass )  || "java.lang.Boolean".equals( propClass )  ){
					Reflection.setProperty( object, propName, new Boolean( propValue ) );
			
			} else if ( "long".equals( propClass )  || "java.lang.Long".equals( propClass )  ){
					Reflection.setProperty( object, propName, new Long( propValue ) );
			
			} else if ( "short".equals( propClass )  || "java.lang.Short".equals( propClass )  ){
					Reflection.setProperty( object, propName, new Short( propValue ) );
			
			} else if ( "char".equals( propClass )  || "java.lang.Character".equals( propClass )  ){
					Reflection.setProperty( object, propName, new Character( propValue.charAt( 0 ) ) );
			
			} else if ( "byte".equals( propClass )  || "java.lang.Byte".equals( propClass )  ){
					Reflection.setProperty( object, propName, new Byte( propValue ) );
			
			} else if ( "java.math.BigDecimal".equals( propClass )  ){
					Reflection.setProperty( object, propName, new BigDecimal( propValue ) );
			} else {	
					Reflection.setProperty( object, propName, propValue );
			}
		}									
	}

	/**
	 * Return the string value of a property in an object.
	 * 
	 * @param object The object.
	 * @param property The property to get.
	 * @return The property value.
	 */
	public static String getStringProperty( Object object, String property ) {
		try {
			return BeanUtils.getProperty( object, property );
		} catch( IllegalAccessException ex ) {
			throw new InvalidPropertyException( property, ex );
		} catch( InvocationTargetException ex ) {
			throw new InvalidPropertyException( property, ex );
		} catch( NoSuchMethodException ex ) {
			throw new InvalidPropertyException( property, ex );
		}
	}

	/**
	 * Instantiate a class.
	 * 
	 * @param clazz The full class name to instantiate.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClass( String clazz ) {
		try {
			return Class.forName( clazz );
		} catch( ClassNotFoundException ex ) {
			throw new RuntimeException( "Class not found: " + clazz, ex );
		} 
	}

	/**
	 * Instantiate a class.
	 * 
	 * @param clazz The full class name to instantiate.
	 * @return
	 */
	public static Object newInstance( String clazz ) {
		try {
			return Class.forName( clazz ).newInstance();
		} catch( ClassNotFoundException ex ) {
			throw new NewInstanceException( clazz, ex );
		} catch( InstantiationException ex ) {
			throw new NewInstanceException( clazz, ex );
		} catch( IllegalAccessException ex ) {
			throw new NewInstanceException( clazz, ex );
		}
	}

	/**
	 * Instantiate a class.
	 * 
	 * @param clazz The class type instantiate.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object newInstance( Class clazz ) {
		try {
			return clazz.newInstance();
		} catch( InstantiationException ex ) {
			throw new NewInstanceException( clazz, ex );
		} catch( IllegalAccessException ex ) {
			throw new NewInstanceException( clazz, ex );
		}
	}
	
	/**
	 * Return the method descriptor.
	 * 
	 * @param methodName The method name.
	 * @param object The target object.
	 * @param parms The admitted method parameters.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Method getMethod( String methodName, Object object, Class[] parms ) {
		return getMethod( methodName, object.getClass(), parms );
	}
	
	/**
	 * Return the method descriptor.
	 * 
	 * @param methodName The method name.
	 * @param clazz The target class.
	 * @param parms The admitted method parameters.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Method getMethod( String methodName, Class clazz, Class[] parms ) {
		try {
			return clazz.getMethod( methodName, parms );
		} catch( SecurityException ex ) {
			throw new InvalidMethodException( methodName, clazz, ex);
		} catch( NoSuchMethodException ex ) {
			log.warn( ex.getLocalizedMessage() );
			return null;
		}
		
	}
	
	/**
	 * Return the method descriptor.
	 * Null value is returned if any error occurs.
	 * 
	 * @param methodName The method name.
	 * @param object The target object.
	 * @param parms The admitted method parameters.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Method findMethod( String methodName, Object object, Class[] parms ) {
		try {
			return getMethod( methodName, object, parms );
		} catch( InvalidMethodException ex ) {
			return null;
		}
	}

	/**
	 * Return the method descriptor.
	 * Null value is returned if any error occurs.
	 * 
	 * @param methodName The method name.
	 * @param clazz The target class.
	 * @param parms The admitted method parameters.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Method findMethod( String methodName, Class clazz, Class[] parms ) {
		try {
			return getMethod( methodName, clazz, parms );
		} catch( InvalidMethodException ex ) {
			return null;
		}
		
	}

	/**
	 * Invoke a method in an target object.
	 * 
	 * @param method The method to invoke.
	 * @param object The target object.
	 * @param args The method arguments.
	 * @return The value returned by the mothod invocation.
	 */
	public static Object invokeMethod( Method method, Object object, Object[] args ) {
		try {
			 return method.invoke( object, args );
		} catch( IllegalArgumentException ex ) {
			throw new InvocationException( method, ex );
		} catch( IllegalAccessException ex ) {
			throw new InvocationException( method, ex );
		} catch( InvocationTargetException ex ) {
			throw new InvocationException( method, ex );
		}
	}
	
	/**
	 * Return the descriptor for a property in a bean.
	 * 
	 * @param bean The target bean.
	 * @param propertyName The property name.
	 * @return
	 */
	public static PropertyDescriptor getPropertyDescriptor( Object bean, String propertyName ) {

		if( bean == null ) {
			throw new PropertyDescriptionException( propertyName );
		}
		try {
			return PropertyUtils.getPropertyDescriptor( bean, propertyName );
		} catch( IllegalAccessException ex ) {
			throw new PropertyDescriptionException( bean, propertyName, ex );
		} catch( InvocationTargetException ex ) {
			throw new PropertyDescriptionException( bean, propertyName, ex );
		} catch( NoSuchMethodException ex ) {
			throw new PropertyDescriptionException( bean, propertyName, ex );
		}
	}

	/**
	 * Return the property descriptor for all the properties in a bean.
	 *  
	 * @param bean The target bean.
	 * @return An array of descriptors.
	 */
	public static PropertyDescriptor[] getPropertyDescriptors( Object bean ) {
		return PropertyUtils.getPropertyDescriptors( bean );
	}
	
	/**
	 * Return the property descriptor for all the properties in a class.
	 *  
	 * @param clazz The target class.
	 * @return An array of descriptors.
	 */
	@SuppressWarnings("rawtypes")
	public static PropertyDescriptor[] getPropertyDescriptors( Class clazz ) {
		return PropertyUtils.getPropertyDescriptors( clazz );
	}
	
	/**
	 * Copy properties from source to destination bean.
	 * 
	 * @param destination Source bean.
	 * @param source Destination bean.
	 */
	public static void copyProperties( Object destination, Object source ) {
		try {
			BeanUtils.copyProperties( destination, source );
		} catch (IllegalAccessException e) {
			throw new RuntimeException( "Errore in copia", e );
		} catch (InvocationTargetException e) {
			throw new RuntimeException( "Errore in copia", e );
		}
	}

	/**
	 * Search in a collection the element having the specified property value.
	 * 
	 * @param coll The collection to search.
	 * @param property The property.
	 * @param value The property value to search.
	 * @param beanClazz The class of the element to return.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object searchBean( Collection coll, String property, Object value, Class beanClazz ) {
		if(coll==null)
			return null;
		Iterator elements = coll.iterator();
		while( elements.hasNext() ) {
			Object element = elements.next();
			Object cfr = Reflection.getProperty( element, property );

			if( cfr != null && cfr.equals( value ) && element.getClass().equals( beanClazz ) ) {
				return element;
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object searchBean(Collection coll, String[] properties, Object obj, Class beanClazz ) {
		if(coll==null)
			return null;
		
		for(int i=0;i<properties.length;i++){	
			Iterator elements = coll.iterator();
			while( elements.hasNext() ) {
				Object element = elements.next();
				Object cfr= Reflection.getProperty( element, properties[i] );
				if( cfr != null && 
				    cfr.equals( Reflection.getProperty( obj, properties[i] ) ) && 
				    element.getClass().equals( beanClazz ) ) {
					return element;
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object searchBean( Collection coll, String property, Object value ) {
		if(coll==null)
			return null;
		Iterator elements = coll.iterator();
		while( elements.hasNext() ) {
			Object element = elements.next();
			Object cfr = Reflection.getProperty( element, property );
			if( cfr != null && cfr.equals( value ) ) {
				return element;
			}
		}
		return null;
	}
	/**
	 * Confronta una lista di propriet&agrave; contenute nell'oggetto obj,con le propriet&agrave;
	 * di tutti gli ogetti contenuti nella collenction coll.
	 * Per il controllo rispetta la priorit&agrave; con la quale viene passato l'array properties 
	 * @param coll
	 * @param properties
	 * @param obj
	 * @return obj 
	 */
	@SuppressWarnings("rawtypes")
	public static Object searchBean( Collection coll, String[] properties, Object obj ) {
		if( coll==null || properties==null || properties.length == 0 )
			return null;
		
		Iterator elements = coll.iterator();
		while( elements.hasNext() ) {
			Object element = elements.next();
			boolean found = true;
			for( int i=0;i<properties.length;i++ ){	
				Object cfr= Reflection.getProperty( element, properties[i] );
	
				found = found && ( cfr != null && cfr.equals( Reflection.getProperty( obj, properties[i] ) ) );
			}
			if( found ) {
				return element;
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object searchIndex( List list, String index, String property, Object value ) {
		for (int i = 0; i < list.size(); i++) {
			Object element = list.get( i );
			Object cfr = Reflection.getProperty( element, property );
			if ( !index.equals( String.valueOf( i ) ) &&  cfr.equals( value ) ) {
				return index;
			}			
		}
		return null;
	}	
	
    /**
     * Metodo deepCopyProperties
     * Metodo che clona un oggetto
     * 
     * @param destination l'oggetto di destinazione 
     * @param source l'oggetto sorgente  
     * 
     */
    public static void deepCopyProperties( Object destination, Object source ) {
        // Se non viene specificato l'oggetto di destinazione o l'oggetto sorgente
        // avviso dell'errore
        if (destination == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (source == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }    
        
        // Se l'oggetto sorgente e' instanza di DynaBean
        if (source instanceof DynaBean){
            copyDynaBeanClass( destination, source );           
        }else{
        	copySimpleClass(destination,source);
        }
    }
    
    private static void copyDynaBeanClass( Object destination, Object source ){
    	try {
    		if(((DynaBean) source).getDynaClass()==null)
    			return;
    		//PropertyUtils.copyProperties(destination,source);
	        DynaProperty origDescriptors[] = ((DynaBean) source).getDynaClass().getDynaProperties();
	        // Per ogni attributo
	        for (int i = 0; i < origDescriptors.length; i++) {
	            // prelevo il nome e oggetto puntato
	            String name = origDescriptors[i].getName();
	            Object srcValue = ((DynaBean) source).get(name);
	            
	            if( ( (DynaBean)destination ).getDynaClass() !=null  ){
	            if (srcValue instanceof DynaBean){
	            	
		            	Object destValue=PropertyUtils.getProperty(destination,name);
		            	deepCopyProperties(destValue,srcValue);
	            	
	            }else{
	            	Reflection.setProperty(destination,name,srcValue);
	            }
	           }
	        }
		} catch (Exception e) {
			throw new RuntimeException("Errore '"+e.getMessage()+"' copiando DynaBean");
		}
    }
    
    /**
     * Metodo copySimpleClass
     * Metodo che copia una classe 
     * 
     * @param destination: Oggetto di destinazione 
     * @param source: Oggetto sorgente  
     * 
     */
    @SuppressWarnings("rawtypes")
    private static void copySimpleClass(Object destination, Object source) {
        // Recupero le descrizioni di ogni singolo attributo della classe source
        PropertyDescriptor[] proDescr = PropertyUtils.getPropertyDescriptors(source);
        
        // Per ogni attributo
        for (int i=0;i<proDescr.length;i++){
            // prelevo il nome e il tipo
            Class propType=proDescr[i].getPropertyType();
            String propName=proDescr[i].getName();
            try {
                if (!"class".equals(propName)){
                    Object propValue=PropertyUtils.getProperty(source,propName);
                    /*
                     * Se la proprieta' e' un tipo primitivo,una stringa o una sottoclasse di Number
                     * Setta il valore
                     * */
                    if ( propType.isPrimitive() || 
                         String.class.isAssignableFrom(propType) ||
                         Number.class.isAssignableFrom(propType)
                       ) {
                        PropertyUtils.setProperty(destination,propName,PropertyUtils.getProperty(source,propName));
                    }else if (propValue!=null){
                        /*
                         * Altrimenti la proprieta' e' instanza di una classe
                         * Se l'oggetto non e' null
                         * Instanzio la classe sull'oggetto destination e chiamo ricorsivamente la funzione
                         * passando i due oggetti da copiare 
                         * */
                        Object obj=Reflection.newInstance(propValue.getClass().getName());
                        PropertyUtils.setProperty(destination,propName,obj);
                        deepCopyProperties(obj,propValue);
                    }
                }
            } catch (Exception e ) {
                throw new RuntimeException("Errore copiando la proprieta `"+propName+"`");
            }
        }
    }	
    
    public static String getPropertyName( Method method ){
    	
		String tmp =method.getName().substring( 3 );
		StringBuffer buffer = new StringBuffer();
		return buffer.append(new Character(tmp.charAt(0)).toString().toLowerCase() )
		   .append(tmp.substring(1)).toString();
	}

    public static Throwable unpackThrowable(Throwable throwable) {

		Throwable result = throwable;

		while (result instanceof InvocationTargetException
				|| result instanceof UndeclaredThrowableException
				|| result instanceof InvocationException) {
			if (result instanceof InvocationTargetException) {
				result = ((InvocationTargetException) result).getCause();
			
			} else if (result instanceof UndeclaredThrowableException) {
				result = ((UndeclaredThrowableException) result).getUndeclaredThrowable();

			} else if (result instanceof InvocationException) {
				result = result.getCause();
			}
		}

		return result;
	}
    
    public static Exception unpackException(Throwable throwable) {
    	Throwable unpacked = unpackThrowable( throwable );
    	if( unpacked instanceof Exception ) {
    		return (Exception)unpacked;
    	} else {
    		return new Exception( unpacked );
    	}
    	
    }

    @SuppressWarnings({ "rawtypes" })
   	private static final Set<Class> WRAPPER_TYPES = new HashSet(Arrays.asList( Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class, String.class, Date.class, BigDecimal.class) );
   	@SuppressWarnings("rawtypes")
   	public static boolean isWrapperType(Class clazz) {
   	    return WRAPPER_TYPES.contains(clazz);
   	}
   	
   	/**
   	 * Recupera il valore di una proprieta' privata nell'oggetto specificato
   	 * @param obj
   	 * @param fieldName
   	 * @return
   	 */
   	public static Object getPrivateFieldValue(Object obj, String fieldName) {
   		try {
   			Field field = obj.getClass().getDeclaredField( fieldName ); 
   			field.setAccessible(true);
			return field.get(obj);
		} catch (IllegalArgumentException e) {
			log.error( e );		
		} catch (IllegalAccessException e) {
			log.error( e );	
		} catch (NoSuchFieldException e) {
			log.error( e );	
		} catch (SecurityException e) {
			log.error( e );	
		}
   		return null;
   	}


}

