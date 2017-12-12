package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day12 {
	private static final Pattern PATTERN = Pattern.compile("^([0-9]+) <-> ((?:[0-9]+, )*[0-9]+)$");

	public static final class Pipe {
		private final int id;
		private final Set<Pipe> neighbours = new HashSet<>();

		private Pipe(int id) {
			this.id = id;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Pipe pipe = (Pipe) o;
			return id == pipe.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
	}

	public static void main(String[] args) throws IOException {
		Pipe root = readPipes(AdventUtils.readLines("day12.txt"));
		System.out.println(count(root));
	}

	public static Pipe readPipes(List<String> lines) {
		Map<Integer, Pipe> pipes = new HashMap<>();

		for (String line : lines) {
			Matcher matcher = PATTERN.matcher(line);
			if (!matcher.matches()) {
				throw new IllegalArgumentException();
			}

			int id = Integer.parseInt(matcher.group(1));
			int[] neighbours = Arrays.stream(matcher.group(2).split(", "))
				.mapToInt(Integer::parseInt)
				.toArray();

			Pipe pipe = pipes.merge(id, new Pipe(id), (a, b) -> a);

			for (int neighbourId : neighbours) {
				Pipe neighbour = pipes.merge(neighbourId, new Pipe(neighbourId), (a, b) -> a);
				pipe.neighbours.add(neighbour);
				neighbour.neighbours.add(pipe);
			}
		}

		return pipes.get(0);
	}

	private static int count(Pipe root, Set<Pipe> seen) {
		int count = 1;
		seen.add(root);

		for (Pipe neighbour : root.neighbours) {
			if (!seen.contains(neighbour)) {
				count += count(neighbour, seen);
			}
		}

		return count;
	}

	public static int count(Pipe root) {
		return count(root, new HashSet<>());
	}
}
