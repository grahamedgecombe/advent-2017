package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public final class Day3 {
	private static final class Position {
		private final int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Position left() {
			return new Position(x - 1, y);
		}

		public Position right() {
			return new Position(x + 1, y);
		}

		public Position up() {
			return new Position(x, y + 1);
		}

		public Position down() {
			return new Position(x, y - 1);
		}

		public Stream<Position> adjacent() {
			Stream.Builder<Position> stream = Stream.builder();

			for (int dx = -1; dx <= 1; dx++) {
				for (int dy =-1; dy <= 1; dy++) {
					if (dx == 0 && dy == 0)
						continue;

					stream.add(new Position(x + dx, y + dy));
				}
			}

			return stream.build();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Position position = (Position) o;
			return x == position.x &&
				y == position.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	public static void main(String[] args) throws IOException {
		int square = Integer.parseInt(AdventUtils.readString("day3.txt"));
		System.out.println(steps(square));
		System.out.println(sumLargerThan(square));
	}

	public static int steps(int square) {
		if (square == 1) {
			return 0;
		}

		int radius = (int) Math.ceil((Math.sqrt(square) + 1) / 2) - 1;
		int diameter = radius * 2;

		int firstSquare = (2 * radius - 1) * (2 * radius - 1) + 1;
		int position = square - firstSquare;

		int x;
		int y;

		if (position < diameter) {
			/* right */
			x = radius;
			y = -radius + position + 1;
		} else if (position < 2 * diameter) {
			/* top */
			x = radius - (position - diameter) - 1;
			y = radius;
		} else if (position < 3 * diameter) {
			/* left */
			x = -radius;
			y = -radius + (position - 2 * diameter) - 1;
		} else {
			/* bottom */
			x = -radius + (position - 3 * diameter) + 1;
			y = -radius;
		}

		return Math.abs(x) + Math.abs(y);
	}

	public static int sumLargerThan(int in) {
		Map<Position, Integer> squares = new HashMap<>();
		Position pos = new Position(0, 0);
		squares.put(pos, 1);

		int radius = 1;

		for (;;) {
			/* right */
			for (int i = 0; i < radius; i++) {
				pos = pos.right();

				int v = pos.adjacent()
					.mapToInt(p -> squares.getOrDefault(p, 0))
					.sum();

				if (v > in) {
					return v;
				}

				squares.put(pos, v);
			}

			/* up */
			for (int i = 0; i < radius; i++) {
				pos = pos.up();

				int v = pos.adjacent()
					.mapToInt(p -> squares.getOrDefault(p, 0))
					.sum();

				if (v > in) {
					return v;
				}

				squares.put(pos, v);
			}

			/* left */
			for (int i = 0; i <= radius; i++) {
				pos = pos.left();

				int v = pos.adjacent()
					.mapToInt(p -> squares.getOrDefault(p, 0))
					.sum();

				if (v > in) {
					return v;
				}

				squares.put(pos, v);
			}

			/* down */
			for (int i = 0; i <= radius; i++) {
				pos = pos.down();

				int v = pos.adjacent()
					.mapToInt(p -> squares.getOrDefault(p, 0))
					.sum();

				if (v > in) {
					return v;
				}

				squares.put(pos, v);
			}

			radius += 2;
		}
	}
}
