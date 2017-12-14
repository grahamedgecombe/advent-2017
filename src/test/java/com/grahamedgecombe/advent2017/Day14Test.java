package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day14Test {
	@Test
	public void testPart1() {
		Day14.Grid grid = Day14.Grid.create("flqrgnkx");
		assertEquals(8108, grid.count());
	}
}
