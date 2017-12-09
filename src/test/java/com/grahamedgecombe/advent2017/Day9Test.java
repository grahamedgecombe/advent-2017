package com.grahamedgecombe.advent2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day9Test {
	@Test
	public void testPart1() {
		assertEquals(1, Day9.score("{}"));
		assertEquals(6, Day9.score("{{{}}}"));
		assertEquals(5, Day9.score("{{},{}}"));
		assertEquals(16, Day9.score("{{{},{},{{}}}}"));
		assertEquals(1, Day9.score("{<a>,<a>,<a>,<a>}"));
		assertEquals(9, Day9.score("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
		assertEquals(9, Day9.score("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
		assertEquals(3, Day9.score("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
	}
}
