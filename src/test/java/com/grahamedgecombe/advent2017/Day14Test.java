package com.grahamedgecombe.advent2017;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day14Test {
	private Day14.Grid grid;

	@Before
	public void setUp() {
		grid = Day14.Grid.create("flqrgnkx");
	}

	@Test
	public void testPart1() {
		assertEquals(8108, grid.count());
	}

	@Test
	public void testPart2() {
		assertEquals(1242, grid.countRegions());
	}
}
