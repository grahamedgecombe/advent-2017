package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day1Test {
	@Test
	public void testPart1() {
		assertEquals(3, Day1.sum("1122"));
		assertEquals(4, Day1.sum("1111"));
		assertEquals(0, Day1.sum("1234"));
		assertEquals(9, Day1.sum("91212129"));
	}
}
