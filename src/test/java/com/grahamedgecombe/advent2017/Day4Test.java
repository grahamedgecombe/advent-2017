package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class Day4Test {
	@Test
	public void testPart1() {
		assertTrue(Day4.isValidPart1("aa bb cc dd ee"));
		assertFalse(Day4.isValidPart1("aa bb cc dd aa"));
		assertTrue(Day4.isValidPart1("aa bb cc dd aaa"));
	}

	@Test
	public void testPart2() {
		assertTrue(Day4.isValidPart2("abcde fghij"));
		assertFalse(Day4.isValidPart2("abcde xyz ecdab"));
		assertTrue(Day4.isValidPart2("a ab abc abd abf abj"));
		assertTrue(Day4.isValidPart2("iiii oiii ooii oooi oooo"));
		assertFalse(Day4.isValidPart2("oiii ioii iioi iiio"));
	}
}
