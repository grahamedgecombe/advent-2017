package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Day5 {
	public static void main(String[] args) throws IOException {
		List<Integer> offsets = AdventUtils.readLines("day5.txt").stream().map(Integer::parseInt).collect(Collectors.toList());
		System.out.println(steps(offsets, Day5::nextOffsetPart1));
		System.out.println(steps(offsets, Day5::nextOffsetPart2));
	}

	public static int steps(List<Integer> offsets, Function<Integer, Integer> nextOffset) {
		offsets = new ArrayList<>(offsets);

		int pos = 0, steps = 0;

		while (pos >= 0 && pos < offsets.size()) {
			int offset = offsets.get(pos);
			offsets.set(pos, nextOffset.apply(offset));
			pos += offset;
			steps++;
		}

		return steps;
	}

	public static int nextOffsetPart1(int offset) {
		return offset + 1;
	}

	public static int nextOffsetPart2(int offset) {
		return offset >= 3 ? offset - 1 : offset + 1;
	}
}
