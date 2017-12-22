package com.grahamedgecombe.advent2017;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day22Test {
	private static final Set<Day22.Position> INFECTED_POSITIONS = Day22.parseMap(Arrays.asList(
		"..#",
		"#..",
		"..."
	));

	@Test
	public void testPart1() {
		assertEquals(41, Day22.countBursts(INFECTED_POSITIONS, 70));
		assertEquals(5587, Day22.countBursts(INFECTED_POSITIONS, 10000));
	}
}
