package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day5Test {
	@Test
	public void testPart1() {
		assertEquals(5, Day5.steps(Arrays.asList(0, 3, 0, 1, -3), Day5::nextOffsetPart1));
	}

	@Test
	public void testPart2() {
		assertEquals(10, Day5.steps(Arrays.asList(0, 3, 0, 1, -3), Day5::nextOffsetPart2));
	}
}
