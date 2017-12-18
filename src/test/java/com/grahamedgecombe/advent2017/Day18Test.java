package com.grahamedgecombe.advent2017;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day18Test {
	@Test
	public void testPart1() {
		List<Day18.Instruction> instructions = Day18.parse(Arrays.asList(
			"set a 1",
			"add a 2",
			"mul a a",
			"mod a 5",
			"snd a",
			"set a 0",
			"rcv a",
			"jgz a -1",
			"set a 1",
			"jgz a -2"
		));
		assertEquals(4, Day18.runPart1(instructions));
	}

	@Test
	public void testPart2() {
		List<Day18.Instruction> instructions = Day18.parse(Arrays.asList(
			"snd 1",
			"snd 2",
			"snd p",
			"rcv a",
			"rcv b",
			"rcv c",
			"rcv d"
		));
		assertEquals(3, Day18.runPart2(instructions));
	}
}
