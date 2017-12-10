package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.Arrays;

public final class Day10 {
	public static void main(String[] args) throws IOException {
		int[] lengths = Arrays.stream(AdventUtils.readString("day10.txt").split(","))
			.mapToInt(Integer::parseInt)
			.toArray();
		System.out.println(knot(256, lengths));
	}

	public static int knot(int totalLength, int[] lengths) {
		int[] list = new int[totalLength];
		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}

		int pos = 0, skip = 0;
		for (int length : lengths) {
			reverse(list, pos, length);
			pos += length + skip;
			skip++;
		}

		return list[0] * list[1];
	}

	private static void reverse(int[] list, int pos, int length) {
		for (int i = 0; i < length / 2; i++) {
			int aIndex = (pos + i) % list.length;
			int bIndex = (pos + length - 1 - i) % list.length;

			int a = list[aIndex];
			int b = list[bIndex];

			list[aIndex] = b;
			list[bIndex] = a;
		}
	}
}
