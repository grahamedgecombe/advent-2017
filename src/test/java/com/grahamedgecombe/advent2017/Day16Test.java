package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day16Test {
	@Test
	public void testPart1() {
		assertEquals("baedc", Day16.dance("abcde", Arrays.asList(
			"s1",
			"x3/4",
			"pe/b"
		)));
	}
}
