package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day10Test {
	@Test
	public void testPart1() {
		assertEquals(12, Day10.knot(5, new int[] { 3, 4, 1, 5 }));
	}
}
