package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.List;

public final class Day2 {
	public static void main(String[] args) throws IOException {
		List<String> rows = AdventUtils.readLines("day2.txt");
		System.out.println(checksum(rows));
	}

	public static int checksum(List<String> rows) {
		int checksum = 0;

		for (String row : rows) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			for (String col : row.split("\t")) {
				int v = Integer.parseInt(col);
				min = Math.min(min, v);
				max = Math.max(max, v);
			}

			checksum += max - min;
		}

		return checksum;
	}
}
