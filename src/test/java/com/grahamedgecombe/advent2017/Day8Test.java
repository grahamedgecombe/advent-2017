package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day8Test {
	private Day8.Machine machine;

	@Before
	public void setUp() {
		machine = Day8.readInstructions(Arrays.asList(
			"b inc 5 if a > 1",
			"a inc 1 if b < 5",
			"c dec -10 if a >= 1",
			"c inc -20 if c == 10"
		));
	}

	@Test
	public void testPart1() {
		machine.evaluate();
		assertEquals(1, machine.getMaxFinalRegister());
	}

	@Test
	public void testPart2() {
		machine.evaluate();
		assertEquals(10, machine.getMaxIntermediateRegister());
	}
}
