package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Day17 {
	public static void main(String[] args) throws IOException {
		int steps = Integer.parseInt(AdventUtils.readString("day17.txt"));
		System.out.println(getPart1(steps));
	}

	public static int getPart1(int steps) {
		List<Integer> ring = new ArrayList<>();
		ring.add(0);

		int pos = 0;
		for (int i = 1; i <= 2017; i++) {
			pos = (pos + steps) % ring.size();
			ring.add((pos + 1) % (ring.size() + 1), i);
			pos = (pos + 1) % ring.size();
		}

		return ring.get((pos + 1) % ring.size());
	}
}
