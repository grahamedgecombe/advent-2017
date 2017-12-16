package com.grahamedgecombe.advent2017;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day16Test {
	private static final List<String> MOVES = List.of("s1", "x3/4", "pe/b");

	@Test
	public void testPart1() {
		assertEquals("baedc", Day16.dance("abcde", MOVES, 1));
	}

	@Test
	public void testPart2() {
		assertEquals("ceadb", Day16.dance("abcde", MOVES, 2));
	}
}
