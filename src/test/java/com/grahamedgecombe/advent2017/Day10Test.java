package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day10Test {
	@Test
	public void testPart1() {
		assertEquals(12, Day10.knotSingleRound(5, new int[] { 3, 4, 1, 5 }));
	}

	@Test
	public void testPart2() {
		assertEquals("a2582a3a0e66e6e86e3812dcb672a272", Day10.knotHash(""));
		assertEquals("33efeb34ea91902bb2f59c9920caa6cd", Day10.knotHash("AoC 2017"));
		assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", Day10.knotHash("1,2,3"));
		assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10.knotHash("1,2,4"));
	}
}
