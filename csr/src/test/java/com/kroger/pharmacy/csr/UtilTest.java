package com.kroger.pharmacy.csr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kroger.pharmacy.csr.util.StateMap;

public class UtilTest extends AbstractApplicationTestCase {
	@Test
	public void testStateMap() {
		StateMap stateMap = new StateMap();
		assertEquals("Indiana", stateMap.getStateName("IN"));
		assertEquals("Ohio", stateMap.getStateName("OH"));
		assertEquals("California", stateMap.getStateName("CA"));
	}
}
