package com.grahamedgecombe.advent2017;

import java.util.Objects;
import java.util.stream.Stream;

public final class Position {
	private final int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
