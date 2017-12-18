package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day18Test {
	@Test
	public void testPart1() {
		Day18.Machine machine = Day18.Machine.create(Arrays.asList(
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
		assertEquals(4, machine.run());
	}
}
