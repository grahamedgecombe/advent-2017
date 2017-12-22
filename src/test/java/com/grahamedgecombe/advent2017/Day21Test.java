package com.grahamedgecombe.advent2017;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day21Test {
	@Test
	public void testPart1() {
		Map<Day21.Grid, Day21.Grid> rules = Day21.Grid.createRules(Arrays.asList(
			"../.# => ##./#../...",
			".#./..#/### => #..#/..../..../#..#"
		));
		assertEquals(12, Day21.Grid.INITIAL_PATTERN.transform(rules, 2).count());
	}
}
