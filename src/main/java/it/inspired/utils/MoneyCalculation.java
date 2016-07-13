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

package it.inspired.utils;

import java.math.BigDecimal;

/**
 * Utility class for money.
 * 
 * @author Massimo Romano
 *
 */
public class MoneyCalculation {

	/**
	 * Defined centrally, to allow for easy changes to the rounding mode.
	 */
	private static int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

	/**
	 * Number of decimals to retain. Also referred to as "scale".
	 */
	private static int DECIMALS = 2;
	// An alternate style for this value :
	// private static int DECIMAL_PLACES = Currency.getInstance("USD").getDefaultFractionDigits();

	@SuppressWarnings("unused")
	private static int EXTRA_DECIMALS = 4;

	private static BigDecimal HUNDRED = new BigDecimal("100");

	// ------------------------------------------------------------------------------------------

	public static BigDecimal rounded(BigDecimal aNumber) {
		return aNumber.setScale(DECIMALS, ROUNDING_MODE);
	}

	public static BigDecimal percentage(BigDecimal amount, int percentange) {
		BigDecimal result = amount.multiply( new BigDecimal(percentange) );
		result = result.divide(HUNDRED, ROUNDING_MODE);
		return rounded(result);
	}
}
