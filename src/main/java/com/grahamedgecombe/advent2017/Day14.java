package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.BitSet;

public final class Day14 {
	public static final class Grid {
		public static Grid create(String seed) {
			Grid grid = new Grid();

			for (int y = 0; y < 128; y++) {
				String hash = Day10.knotHash(seed + "-" + y);

				for (int i = 0; i < hash.length(); i++) {
					int nibble = Integer.parseInt(Character.toString(hash.charAt(i)), 16);

					for (int j = 0; j < 4; j++) {
						int x = i * 4 + (3 - j);
						grid.used.set(index(x, y), ((nibble >> j) & 0x1) != 0);
					}
				}
			}

			return grid;
		}

		private static int index(int x, int y) {
			return y * 128 + x;
		}

		private final BitSet used = new BitSet();

		private Grid() {
			/* empty */
		}

		public int count() {
			return used.cardinality();
		}

		private void clearRegion(int x, int y) {
			if (x < 0 || y < 0 || x >= 128 || y >= 128) {
				return;
			}

			int index = index(x, y);
			if (!used.get(index)) {
				return;
			}

			used.clear(index);

			clearRegion(x - 1, y);
			clearRegion(x + 1, y);
			clearRegion(x, y - 1);
			clearRegion(x, y + 1);
		}

		public int countRegions() {
			int regions = 0;

			for (int y = 0; y < 128; y++) {
				for (int x = 0; x < 128; x++) {
					int index = index(x, y);
					if (used.get(index)) {
						regions++;
						clearRegion(x, y);
					}
				}
			}

			return regions;
		}
	}

	public static void main(String[] args) throws IOException {
		Grid grid = Grid.create(AdventUtils.readString("day14.txt"));
		System.out.println(grid.count());
		System.out.println(grid.countRegions());
	}
}
