package com.grahamedgecombe.advent2017;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day20Test {
	@Test
	public void testPart1() {
		assertEquals(0, Day20.getClosestToOrigin(Day20.Particle.parse(Arrays.asList(
			"p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>",
			"p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>"
		))));
	}
}
