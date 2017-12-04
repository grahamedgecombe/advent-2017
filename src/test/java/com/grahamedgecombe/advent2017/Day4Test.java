package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class Day4Test {
	@Test
	public void testPart1() {
		assertTrue(Day4.isValid("aa bb cc dd ee"));
		assertFalse(Day4.isValid("aa bb cc dd aa"));
		assertTrue(Day4.isValid("aa bb cc dd aaa"));
	}
}
