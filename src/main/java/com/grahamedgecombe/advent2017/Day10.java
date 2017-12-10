package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.Arrays;

public final class Day10 {
	public static void main(String[] args) throws IOException {
		String in = AdventUtils.readString("day10.txt");
		int[] lengths = Arrays.stream(in.split(","))
			.mapToInt(Integer::parseInt)
			.toArray();
		System.out.println(knotSingleRound(256, lengths));
		System.out.println(knotHash(in));
	}

	private static int[] knot(int totalLength, int[] lengths, int rounds) {
		int[] list = new int[totalLength];
		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}

		int pos = 0, skip = 0;
		for (int i = 0; i < rounds; i++) {
			for (int length : lengths) {
				reverse(list, pos, length);
				pos += length + skip;
				skip++;
			}
		}

		return list;
	}

	public static int knotSingleRound(int totalLength, int[] lengths) {
		int[] list = knot(totalLength, lengths, 1);
		return list[0] * list[1];
	}

	public static String knotHash(String in) {
		char[] chars = in.toCharArray();

		int[] lengths = new int[chars.length + 5];
		for (int i = 0; i < chars.length; i++) {
			lengths[i] = (int) chars[i];
		}

		lengths[lengths.length - 5] = 17;
		lengths[lengths.length - 4] = 31;
		lengths[lengths.length - 3] = 73;
		lengths[lengths.length - 2] = 47;
		lengths[lengths.length - 1] = 23;

		int[] sparse = knot(256, lengths, 64);
		StringBuilder denseHex = new StringBuilder();

		for (int i = 0; i < 16; i++) {
			int value = sparse[i * 16];

			for (int j = 1; j < 16; j++) {
				value ^= sparse[i * 16 + j];
			}

			String hex = Integer.toUnsignedString(value, 16);
			if (hex.length() == 1) {
				denseHex.append('0');
			} else if (hex.length() != 2) {
				throw new IllegalStateException();
			}
			denseHex.append(hex);
		}

		return denseHex.toString();
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
