package com.grahamedgecombe.advent2017;

import java.io.IOException;

public final class Day1 {
	public static void main(String[] args) throws IOException {
		String list = AdventUtils.readString("day1.txt");
		System.out.println(sum(list));
	}

	public static int sum(String list) {
		int sum = 0;

		for (int i = 0; i < list.length(); i++) {
			char c1 = list.charAt(i);
			char c2 = list.charAt((i + 1) % list.length());

			if (c1 == c2) {
				sum += c1 - '0';
			}
		}

		return sum;
	}
}
