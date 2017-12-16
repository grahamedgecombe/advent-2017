package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day16 {
	private static final Pattern PATTERN = Pattern.compile("^([sxp])((?:[0-9]+)|[a-z])(?:/((?:[0-9]+)|[a-z]))?$");

	public static void main(String[] args) throws IOException {
		List<String> moves = Arrays.asList(AdventUtils.readString("day16.txt").split(","));
		System.out.println(dance("abcdefghijklmnop", moves, 1));
		System.out.println(dance("abcdefghijklmnop", moves, 1000000000));
	}

	public static String dance(String in, List<String> moves, int iterations) {
		char[] chars = in.toCharArray();

		List<String> states = new ArrayList<>();
		states.add(in);

		for (int i = 0; i < iterations; i++) {
			for (String move : moves) {
				Matcher matcher = PATTERN.matcher(move);
				if (!matcher.matches()) {
					throw new IllegalArgumentException();
				}

				switch (matcher.group(1)) {
					case "s":
						spin(chars, Integer.parseInt(matcher.group(2)));
						break;

					case "x":
						partner(chars, Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
						break;

					case "p":
						exchange(chars, matcher.group(2).charAt(0), matcher.group(3).charAt(0));
						break;

					default:
						throw new IllegalArgumentException();
				}
			}

			String state = new String(chars);
			if (state.equals(in)) {
				return states.get(iterations % states.size());
			}

			states.add(state);
		}

		return new String(chars);
	}

	private static void spin(char[] chars, int x) {
		char[] temp = new char[chars.length - x];
		System.arraycopy(chars, 0, temp, 0, temp.length);
		System.arraycopy(chars, temp.length, chars, 0, x);
		System.arraycopy(temp, 0, chars, x, temp.length);
	}

	private static void partner(char[] chars, int a, int b) {
		char temp = chars[a];
		chars[a] = chars[b];
		chars[b] = temp;
	}

	private static int indexOf(char[] chars, char c) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == c) {
				return i;
			}
		}

		throw new IllegalStateException();
	}

	private static void exchange(char[] chars, char a, char b) {
		int aIndex = indexOf(chars, a);
		int bIndex = indexOf(chars, b);
		partner(chars, aIndex, bIndex);
	}
}
