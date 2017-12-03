package com.grahamedgecombe.advent2017;

import java.io.IOException;

public final class Day3 {
	public static void main(String[] args) throws IOException {
		int square = Integer.parseInt(AdventUtils.readString("day3.txt"));
		System.out.println(steps(square));
	}

	public static int steps(int square) {
		if (square == 1) {
			return 0;
		}

		int radius = (int) Math.ceil((Math.sqrt(square) + 1) / 2) - 1;
		int diameter = radius * 2;

		int firstSquare = (2 * radius - 1) * (2 * radius - 1) + 1;
		int position = square - firstSquare;

		int x;
		int y;

		if (position < diameter) {
			/* right */
			x = radius;
			y = -radius + position + 1;
		} else if (position < 2 * diameter) {
			/* top */
			x = radius - (position - diameter) - 1;
			y = radius;
		} else if (position < 3 * diameter) {
			/* left */
			x = -radius;
			y = -radius + (position - 2 * diameter) - 1;
		} else {
			/* bottom */
			x = -radius + (position - 3 * diameter) + 1;
			y = -radius;
		}

		return Math.abs(x) + Math.abs(y);
	}
}
