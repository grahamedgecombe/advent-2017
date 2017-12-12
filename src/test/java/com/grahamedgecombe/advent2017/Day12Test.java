package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day12Test {
	@Test
	public void testPart1() {
		assertEquals(6, Day12.count(Day12.readPipes(Arrays.asList(
			"0 <-> 2",
			"1 <-> 1",
			"2 <-> 0, 3, 4",
			"3 <-> 2, 4",
			"4 <-> 2, 3, 6",
			"5 <-> 6",
			"6 <-> 4, 5"
		))));
	}
}
