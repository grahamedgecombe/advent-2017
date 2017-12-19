package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day19Test {
	private static final String[] MAP = {
		"     |          ",
		"     |  +--+    ",
		"     A  |  C    ",
		" F---|----E|--+ ",
		"     |  |  |  D ",
		"     +B-+  +--+ "
	};

	@Test
	public void testPart1() {
		assertEquals("ABCDEF", Day19.getPath(MAP).getLetters());
	}

	@Test
	public void testPart2() {
		assertEquals(38, Day19.getPath(MAP).getSteps());
	}
}
