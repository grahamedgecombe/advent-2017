package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Day22 {
	public static final class Position {
		public static final Position ORIGIN = new Position(0, 0);

		private final int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Position forward(Direction direction) {
			switch (direction) {
				case NORTH:
					return new Position(x, y - 1);
				case EAST:
					return new Position(x + 1, y);
				case SOUTH:
					return new Position(x, y + 1);
				case WEST:
					return new Position(x - 1, y);
				default:
					throw new IllegalArgumentException();
			}
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
	}

	private enum Direction {
		NORTH,
		EAST,
		SOUTH,
		WEST;

		public Direction left() {
			switch (this) {
				case NORTH:
					return WEST;
				case EAST:
					return NORTH;
				case SOUTH:
					return EAST;
				case WEST:
					return SOUTH;
				default:
					throw new IllegalArgumentException();
			}
		}

		public Direction right() {
			switch (this) {
				case NORTH:
					return EAST;
				case EAST:
					return SOUTH;
				case SOUTH:
					return WEST;
				case WEST:
					return NORTH;
				default:
					throw new IllegalArgumentException();
			}
		}
	}

	public static Set<Position> parseMap(List<String> lines) {
		Set<Position> infectedPositions = new HashSet<>();
		int radius = (lines.size() - 1) / 2;

		for (int y = -radius; y <= radius; y++) {
			for (int x = -radius; x <= radius; x++) {
				if (lines.get(y + radius).charAt(x + radius) == '#') {
					infectedPositions.add(new Position(x, y));
				}
			}
		}

		return infectedPositions;
	}

	public static int countBursts(Set<Position> infectedPositions, int bursts) {
		infectedPositions = new HashSet<>(infectedPositions);

		Position position = Position.ORIGIN;
		Direction direction = Direction.NORTH;
		int infectedBursts = 0;

		for (int i = 0; i < bursts; i++) {
			if (infectedPositions.contains(position)) {
				infectedPositions.remove(position);

				direction = direction.right();
			} else {
				infectedPositions.add(position);
				infectedBursts++;

				direction = direction.left();
			}

			position = position.forward(direction);
		}

		return infectedBursts;
	}

	public static void main(String[] args) throws IOException {
		Set<Position> infectedPositions = parseMap(AdventUtils.readLines("day22.txt"));
		System.out.println(countBursts(infectedPositions, 10000));
	}
}
