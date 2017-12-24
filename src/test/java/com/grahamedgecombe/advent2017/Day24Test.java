package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day24Test {
	@Test
	public void testPart1() {
		assertEquals(31, Day24.getStrongestBridge(Day24.Port.parse(Arrays.asList(
			"0/2",
			"2/2",
			"2/3",
			"3/4",
			"3/5",
			"0/1",
			"10/1",
			"9/10"
		))));
	}
}
