package com.kroger.pharmacy.csr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.kroger.pharmacy.csr.domain.State;

public class StateTest extends AbstractApplicationTestCase {
	@Test
	public void testState() {
		State state = new State("Indiana", "IN");
		assertEquals(state.getName(), "Indiana");
		assertEquals(state.getCode(), "IN");
	}
	
	@Test
	public void testEquals() {
		State state1 = new State("Indiana", "IN");
		State state2 = new State("California", "CA");
		
		assertFalse(state1.equals(state2));
		
		state2 = new State("Indiana", "IN");
		assertTrue(state2.equals(state1));
	}
}
