package com.grahamedgecombe.advent2017;

import java.io.IOException;

public final class Day9 {
	public static void main(String[] args) throws IOException {
		String chars = AdventUtils.readString("day9.txt");
		System.out.println(score(chars));
		System.out.println(garbageChars(chars));
	}

	public static int score(String chars) {
		return parse(chars, true);
	}

	public static int garbageChars(String chars) {
		return parse(chars, false);
	}

	private static int parse(String chars, boolean part1) {
		int score = 0;
		int garbageChars = 0;

		int depth = 0;
		boolean garbage = false;
		boolean ignore = false;

		for (char c : chars.toCharArray()) {
			if (garbage) {
				if (ignore) {
					ignore = false;
				} else {
					switch (c) {
						case '>':
							garbage = false;
							break;
						case '!':
							ignore = true;
							break;
						default:
							garbageChars++;
							break;
					}
				}
			} else {
				switch (c) {
					case '{':
						depth++;
						break;
					case '}':
						if (depth == 0) {
							throw new IllegalArgumentException();
						}

						score += depth;
						depth--;
						break;
					case ',':
						/* ignore */
						break;
					case '<':
						garbage = true;
						break;
					default:
						throw new IllegalArgumentException();
				}
			}
		}

		if (part1) {
			return score;
		} else {
			return garbageChars;
		}
	}
}
