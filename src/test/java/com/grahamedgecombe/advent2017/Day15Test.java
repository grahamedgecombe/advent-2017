package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day15Test {
	@Test
	public void testPart1() {
		assertEquals(1, Day15.countMatches(65, 8921, 5, true));
	}

	@Test
	public void testPart2() {
		assertEquals(309, Day15.countMatches(65, 8921, 5000000, false));
	}
}
