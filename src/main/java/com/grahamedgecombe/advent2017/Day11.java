package com.grahamedgecombe.advent2017;

import java.io.IOException;

public final class Day11 {
	public static void main(String[] args) throws IOException {
		String[] steps = AdventUtils.readString("day11.txt").split(",");
		System.out.println(getFinalDistance(steps));
		System.out.println(getMaxDistance(steps));
	}

	public static int getFinalDistance(String[] steps) {
		return getDistance(steps, true);
	}

	public static int getMaxDistance(String[] steps) {
		return getDistance(steps, false);
	}

	private static int getDistance(String[] steps, boolean part1) {
		int q = 0, r = 0, maxDistance = 0;

		for (String step : steps) {
			switch (step) {
				case "n":
					r--;
					break;
				case "ne":
					q++;
					r--;
					break;
				case "se":
					q++;
					break;
				case "s":
					r++;
					break;
				case "sw":
					q--;
					r++;
					break;
				case "nw":
					q--;
					break;
				default:
					throw new IllegalArgumentException();
			}

			int distance = Math.max(Math.max(Math.abs(q), Math.abs(r)), Math.abs(-q - r));
			maxDistance = Math.max(maxDistance, distance);
		}

		int finalDistance = Math.max(Math.max(Math.abs(q), Math.abs(r)), Math.abs(-q - r));
		if (part1) {
			return finalDistance;
		} else {
			return maxDistance;
		}
	}
}
