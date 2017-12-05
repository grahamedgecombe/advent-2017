package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public final class Day5 {
	public static void main(String[] args) throws IOException {
		List<Integer> offsets = AdventUtils.readLines("day5.txt").stream().map(Integer::parseInt).collect(Collectors.toList());
		System.out.println(steps(offsets));
	}

	public static int steps(List<Integer> offsets) {
		int pos = 0, steps = 0;

		while (pos >= 0 && pos < offsets.size()) {
			int offset = offsets.get(pos);
			offsets.set(pos, offset + 1);
			pos += offset;
			steps++;
		}

		return steps;
	}
}
