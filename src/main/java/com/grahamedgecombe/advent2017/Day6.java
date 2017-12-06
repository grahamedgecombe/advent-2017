package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Day6 {
	public static void main(String[] args) throws IOException {
		List<Integer> banks = Arrays.stream(AdventUtils.readString("day6.txt").split("\t"))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
		System.out.println(reallocate(banks));
	}

	public static int reallocate(List<Integer> banks) {
		Set<List<Integer>> seen = new HashSet<>();
		int count = 0;

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
		} while (seen.add(new ArrayList<>(banks)));

		return count;
	}
}
