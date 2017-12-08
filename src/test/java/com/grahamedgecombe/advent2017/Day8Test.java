package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day8Test {
	@Test
	public void testPart1() {
		Day8.Machine machine = Day8.readInstructions(Arrays.asList(
			"b inc 5 if a > 1",
			"a inc 1 if b < 5",
			"c dec -10 if a >= 1",
			"c inc -20 if c == 10"
		));
		machine.evaluate();
		assertEquals(1, machine.getMaxRegister());
	}
}
