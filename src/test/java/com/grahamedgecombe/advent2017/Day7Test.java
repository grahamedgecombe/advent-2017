package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day7Test {
	private Day7.Program root;

	@Before
	public void setUp() {
		root = Day7.readDiscs(Arrays.asList(
			"pbga (66)",
			"xhth (57)",
			"ebii (61)",
			"havc (66)",
			"ktlj (57)",
			"fwft (72) -> ktlj, cntj, xhth",
			"qoyq (66)",
			"padx (45) -> pbga, havc, qoyq",
			"tknk (41) -> ugml, padx, fwft",
			"jptl (61)",
			"ugml (68) -> gyxo, ebii, jptl",
			"gyxo (61)",
			"cntj (57)"
		));
	}

	@Test
	public void testPart1() {
		assertEquals("tknk", root.getName());
	}

	@Test
	public void testPart2() {
		assertEquals(60, root.balance());
	}
}
