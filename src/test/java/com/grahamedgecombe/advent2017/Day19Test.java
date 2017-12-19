package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day19Test {
	@Test
	public void testPart1() {
		assertEquals("ABCDEF", Day19.getPath(new String[] {
			"     |          ",
			"     |  +--+    ",
			"     A  |  C    ",
			" F---|----E|--+ ",
			"     |  |  |  D ",
			"     +B-+  +--+ "
		}));
	}
}
