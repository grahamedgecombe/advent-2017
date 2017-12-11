package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day11Test {
	@Test
	public void testPart1() {
		assertEquals(3, Day11.getDistance(new String[] { "ne", "ne", "ne" }));
		assertEquals(0, Day11.getDistance(new String[] { "ne", "ne", "sw", "sw" }));
		assertEquals(2, Day11.getDistance(new String[] { "ne", "ne", "s", "s" }));
		assertEquals(3, Day11.getDistance(new String[] { "se", "sw", "se", "sw", "sw" }));
	}
}
