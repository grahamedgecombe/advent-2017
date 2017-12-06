package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day6Test {
	@Test
	public void testPart1() {
		assertEquals(5, Day6.reallocate(Arrays.asList(0, 2, 7, 0)));
	}
}
