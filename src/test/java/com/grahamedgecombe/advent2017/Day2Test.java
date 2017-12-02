package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day2Test {
	@Test
	public void testPart1() {
		assertEquals(18, Day2.checksum(Arrays.asList("5\t1\t9\t5", "7\t5\t3", "2\t4\t6\t8")));
	}
}