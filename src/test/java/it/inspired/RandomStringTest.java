package it.inspired;

import static org.junit.Assert.*;
import it.inspired.random.RandomIdentifier;

import org.junit.Test;

public class RandomStringTest {

	@Test
	public void testRandomIdentifier() {
		
		RandomIdentifier ri = new RandomIdentifier();
		
		String str = ri.generate();
		
		assertNotNull( str );
		
	}

}
