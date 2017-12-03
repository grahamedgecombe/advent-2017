package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day3Test {
	@Test
	public void testPart1() {
		assertEquals(0, Day3.steps(1));
		assertEquals(3, Day3.steps(12));
		assertEquals(2, Day3.steps(23));
		assertEquals(31, Day3.steps(1024));
	}
}
