package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Day6 {
	public static void main(String[] args) throws IOException {
		List<Integer> banks = Arrays.stream(AdventUtils.readString("day6.txt").split("\t"))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
		System.out.println(reallocatePart1(banks));
		System.out.println(reallocatePart2(banks));
	}

	public static int reallocatePart1(List<Integer> banks) {
		return reallocate(banks, true);
	}

	public static int reallocatePart2(List<Integer> banks) {
		return reallocate(banks, false);
	}

	private static int reallocate(List<Integer> banks, boolean part1) {
		banks = new ArrayList<>(banks);

		Map<List<Integer>, Integer> seen = new HashMap<>();
		int count = 0;
		Integer firstSeen;

		do {
			int maxBank = 0;
			int maxBlocks = banks.get(0);

			for (int i = 1; i < banks.size(); i++) {
				int blocks = banks.get(i);

				if (blocks > maxBlocks) {
					maxBank = i;
					maxBlocks = blocks;
				}
			}

			int bank = maxBank;
			int blocks = maxBlocks;

			banks.set(maxBank, 0);

			while (blocks > 0) {
				bank = (bank + 1) % banks.size();

				banks.set(bank, banks.get(bank) + 1);
				blocks--;
			}

			count++;
			firstSeen = seen.put(banks, count);
		} while (firstSeen == null);

		if (part1) {
			return count;
		} else {
			return count - firstSeen;
		}
	}
}
