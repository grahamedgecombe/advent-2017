package com.grahamedgecombe.advent2017;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day24Test {
	private static final List<Day24.Port> PORTS = Day24.Port.parse(Arrays.asList(
		"0/2",
		"2/2",
		"2/3",
		"3/4",
		"3/5",
		"0/1",
		"10/1",
		"9/10"
	));

	@Test
	public void testPart1() {
		assertEquals(31, Day24.getStrongestBridge(PORTS));
	}

	@Test
	public void testPart2() {
		assertEquals(19, Day24.getLongestBridgeStrength(PORTS));
	}
}
