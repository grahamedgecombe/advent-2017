package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public final class Day2 {
	public static void main(String[] args) throws IOException {
		List<String> rows = AdventUtils.readLines("day2.txt");
		System.out.println(checksumPart1(rows));
		System.out.println(checksumPart2(rows));
	}

	public static int checksumPart1(List<String> rows) {
		int checksum = 0;

		for (String row : rows) {
			IntSummaryStatistics s = Arrays.stream(row.split("\t"))
				.mapToInt(Integer::parseInt)
				.summaryStatistics();

			checksum += s.getMax() - s.getMin();
		}

		return checksum;
	}

	public static int checksumPart2(List<String> rows) {
		int checksum = 0;

		for (String row : rows) {
			List<Integer> cols = Arrays.stream(row.split("\t"))
				.map(Integer::parseInt)
				.collect(Collectors.toList());

			next_row: for (int i = 0; i < cols.size(); i++) {
				for (int j = 0; j < i; j++) {
					int v1 = cols.get(i);
					int v2 = cols.get(j);

					if ((v1 % v2) == 0) {
						checksum += v1 / v2;
						break next_row;
					} else if ((v2 % v1) == 0) {
						checksum += v2 / v1;
						break next_row;
					}
				}
			}
		}

		return checksum;
	}
}
