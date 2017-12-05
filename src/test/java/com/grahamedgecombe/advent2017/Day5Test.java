package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day5Test {
	@Test
	public void testPart1() {
		assertEquals(5, Day5.steps(Arrays.asList(0, 3, 0, 1, -3)));
	}
}
