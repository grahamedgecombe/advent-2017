package com.grahamedgecombe.advent2017;

import java.io.IOException;

public final class Day11 {
	public static void main(String[] args) throws IOException {
		String[] steps = AdventUtils.readString("day11.txt").split(",");
		System.out.println(getDistance(steps));
	}

	public static int getDistance(String[] steps) {
		int q = 0, r = 0;

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
		}

		return Math.max(Math.max(Math.abs(q), Math.abs(r)), Math.abs(-q - r));
	}
}
