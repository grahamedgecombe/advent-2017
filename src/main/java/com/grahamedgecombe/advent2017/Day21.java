package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Day21 {
	public static final class Grid {
		public static final Grid INITIAL_PATTERN = fromString(".#./..#/###");

		public static Grid fromString(String in) {
			String[] rows = in.split("/");
			Grid grid = new Grid(rows.length);

			for (int y = 0; y < grid.size; y++) {
				for (int x = 0; x < grid.size; x++) {
					grid.pixels.set(grid.index(x, y), rows[y].charAt(x) == '#');
				}
			}

			return grid;
		}

		public static Map<Grid, Grid> createRules(List<String> lines) {
			Map<Grid, Grid> rules = new HashMap<>();

			for (String line : lines) {
				String[] parts = line.split(" => ");
				if (parts.length != 2) {
					throw new IllegalArgumentException();
				}

				Grid grid = Grid.fromString(parts[0]);
				Grid replacement = Grid.fromString(parts[1]);

				for (int i = 0; i < 4; i++) {
					rules.put(grid, replacement);
					grid = grid.rotate90();
				}

				grid = grid.flip();

				for (int i = 0; i < 4; i++) {
					rules.put(grid, replacement);
					grid = grid.rotate90();
				}
			}

			return rules;
		}

		private final BitSet pixels;
		private final int size;

		public Grid(int size) {
			this.pixels = new BitSet(size * size);
			this.size = size;
		}

		private int index(int x, int y) {
			return y * size + x;
		}

		public Grid flip() {
			Grid grid = new Grid(size);

			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					grid.pixels.set(grid.index(size - x - 1, y), pixels.get(index(x, y)));
				}
			}

			return grid;
		}

		public Grid rotate90() {
			Grid grid = new Grid(size);

			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					grid.pixels.set(grid.index(size - y - 1, x), pixels.get(index(x, y)));
				}
			}

			return grid;
		}

		public int count() {
			return pixels.cardinality();
		}

		public Grid subgrid(int x, int y, int size) {
			Grid grid = new Grid(size);

			for (int y0 = 0; y0 < size; y0++) {
				for (int x0 = 0; x0 < size; x0++) {
					grid.pixels.set(grid.index(x0, y0), pixels.get(index(x + x0, y + y0)));
				}
			}

			return grid;
		}

		public void setSubgrid(int x, int y, Grid subgrid) {
			for (int y0 = 0; y0 < subgrid.size; y0++) {
				for (int x0 = 0; x0 < subgrid.size; x0++) {
					pixels.set(index(x + x0, y + y0), subgrid.pixels.get(subgrid.index(x0, y0)));
				}
			}
		}

		public Grid transform(Map<Grid, Grid> rules) {
			int newSize, oldSubgridSize, newSubgridSize;

			if ((size % 2) == 0) {
				newSize = size / 2 * 3;
				oldSubgridSize = 2;
				newSubgridSize = 3;
			} else {
				newSize = size / 3 * 4;
				oldSubgridSize = 3;
				newSubgridSize = 4;
			}

			Grid grid = new Grid(newSize);

			for (int srcX = 0, destX = 0; srcX < size; srcX += oldSubgridSize, destX += newSubgridSize) {
				for (int srcY = 0, destY = 0; srcY < size; srcY += oldSubgridSize, destY += newSubgridSize) {
					Grid subgrid = rules.get(subgrid(srcX, srcY, oldSubgridSize));
					grid.setSubgrid(destX, destY, subgrid);
				}
			}

			return grid;
		}

		public Grid transform(Map<Grid, Grid> rules, int iterations) {
			Grid grid = INITIAL_PATTERN;
			for (int i = 0; i < iterations; i++) {
				grid = grid.transform(rules);
			}
			return grid;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Grid grid = (Grid) o;
			return size == grid.size &&
				Objects.equals(pixels, grid.pixels);
		}

		@Override
		public int hashCode() {
			return Objects.hash(pixels, size);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();

			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					builder.append(pixels.get(index(x, y)) ? '#' : '.');
				}

				if (y != (size - 1)) {
					builder.append('/');
				}
			}

			return builder.toString();
		}
	}

	public static void main(String[] args) throws IOException {
		Map<Grid, Grid> rules = Grid.createRules(AdventUtils.readLines("day21.txt"));
		System.out.println(Grid.INITIAL_PATTERN.transform(rules, 5).count());
	}
}
