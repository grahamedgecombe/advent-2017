package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day25Test {
	@Test
	public void testPart1() {
		Day25.TuringMachine machine = Day25.TuringMachine.parse(Arrays.asList(
			"Begin in state A.",
			"Perform a diagnostic checksum after 6 steps.",
			"",
			"In state A:",
			"  If the current value is 0:",
			"    - Write the value 1.",
			"    - Move one slot to the right.",
			"    - Continue with state B.",
			"  If the current value is 1:",
			"    - Write the value 0.",
			"    - Move one slot to the left.",
			"    - Continue with state B.",
			"",
			"In state B:",
			"  If the current value is 0:",
			"    - Write the value 1.",
			"    - Move one slot to the left.",
			"    - Continue with state A.",
			"  If the current value is 1:",
			"    - Write the value 1.",
			"    - Move one slot to the right.",
			"    - Continue with state A."
		));
		assertEquals(3, machine.run());
	}
}
