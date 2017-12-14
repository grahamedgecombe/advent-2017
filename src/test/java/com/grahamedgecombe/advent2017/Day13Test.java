package com.grahamedgecombe.advent2017;

import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day13Test {
	@Test
	public void testPart1() {
		assertEquals(24, Day13.getSeverity(Map.of(
			0, 3,
			1, 2,
			4, 4,
			6, 4
		)));
	}
}
