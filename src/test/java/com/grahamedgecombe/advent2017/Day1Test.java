package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day1Test {
	@Test
	public void testPart1() {
		assertEquals(3, Day1.sumPart1("1122"));
		assertEquals(4, Day1.sumPart1("1111"));
		assertEquals(0, Day1.sumPart1("1234"));
		assertEquals(9, Day1.sumPart1("91212129"));
	}

	@Test
	public void testPart2() {
		assertEquals(6, Day1.sumPart2("1212"));
		assertEquals(0, Day1.sumPart2("1221"));
		assertEquals(4, Day1.sumPart2("123425"));
		assertEquals(12, Day1.sumPart2("123123"));
		assertEquals(4, Day1.sumPart2("12131415"));
	}
}
