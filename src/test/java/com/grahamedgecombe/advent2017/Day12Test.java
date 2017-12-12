package com.grahamedgecombe.advent2017;

import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day12Test {
	private Map<Integer, Day12.Pipe> pipes;

	@Before
	public void setUp() {
		pipes = Day12.readPipes(Arrays.asList(
			"0 <-> 2",
			"1 <-> 1",
			"2 <-> 0, 3, 4",
			"3 <-> 2, 4",
			"4 <-> 2, 3, 6",
			"5 <-> 6",
			"6 <-> 4, 5"
		));
	}

	@Test
	public void testPart1() {
		assertEquals(6, Day12.count(pipes));
	}

	@Test
	public void testPart2() {
		assertEquals(2, Day12.groups(pipes));
	}
}
