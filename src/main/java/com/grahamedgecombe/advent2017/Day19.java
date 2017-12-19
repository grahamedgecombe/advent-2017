package com.grahamedgecombe.advent2017;

import java.io.IOException;

public final class Day19 {
	private enum Direction {
		NORTH(0, -1),
		EAST(1, 0),
		SOUTH(0, 1),
		WEST(-1, 0);

		private final int dx, dy;

		Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}

		private Direction opposite() {
			switch (this) {
				case NORTH:
					return SOUTH;
				case EAST:
					return WEST;
				case SOUTH:
					return NORTH;
				case WEST:
					return EAST;
			}

			throw new AssertionError();
		}
	}

	public static void main(String[] args) throws IOException {
		String[] grid = AdventUtils.readLines("day19.txt").toArray(new String[0]);
		System.out.println(getPath(grid));
	}

	public static String getPath(String[] grid) {
		StringBuilder path = new StringBuilder();

		int startX = -1;
		for (int x = 0; x < grid[0].length(); x++) {
			if (grid[0].charAt(x) == '|') {
				startX = x;
				break;
			}
		}

		if (startX == -1) {
			throw new IllegalArgumentException();
		}

		int x = startX, y = 0;
		Direction direction = Direction.SOUTH;

		for (;;) {
			char tile = grid[y].charAt(x);

			if (tile >= 'A' && tile <= 'Z') {
				path.append(tile);
			} else if (tile == '+') {
				for (Direction d : Direction.values()) {
					if (direction == d.opposite()) {
						continue;
					}

					int x0 = x + d.dx, y0 = y + d.dy;
					if (y0 < 0 || y0 >= grid.length || x0 < 0 || x0 >= grid[y0].length()) {
						continue;
					}

					if (grid[y0].charAt(x0) != ' ') {
						direction = d;
						break;
					}
				}
			} else if (tile == ' ') {
				break;
			}

			x += direction.dx;
			y += direction.dy;
		}

		return path.toString();
	}
}
