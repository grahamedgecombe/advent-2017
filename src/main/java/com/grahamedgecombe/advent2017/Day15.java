package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day15 {
	private static final Pattern PATTERN = Pattern.compile("([0-9]+)");

	public static void main(String[] args) throws IOException {
		Matcher matcher = PATTERN.matcher(AdventUtils.readString("day15.txt"));

		if (!matcher.find()) {
			throw new IllegalArgumentException();
		}
		int aValue = Integer.parseInt(matcher.group(1));

		if (!matcher.find()) {
			throw new IllegalArgumentException();
		}
		int bValue = Integer.parseInt(matcher.group(1));

		System.out.println(countMatches(aValue, bValue, 40000000, true));
		System.out.println(countMatches(aValue, bValue, 5000000, false));
	}

	public static int countMatches(int aValue, int bValue, int pairs, boolean part1) {
		int count = 0;

		for (int i = 0; i < pairs; i++) {
			do {
				aValue = (int) ((aValue * 16807L) % Integer.MAX_VALUE);
			} while (!part1 && (aValue & 3) != 0);

			do {
				bValue = (int) ((bValue * 48271L) % Integer.MAX_VALUE);
			} while (!part1 && (bValue & 7) != 0);

			if ((aValue & 0xFFFF) == (bValue & 0xFFFF)) {
				count++;
			}
		}

		return count;
	}
}
