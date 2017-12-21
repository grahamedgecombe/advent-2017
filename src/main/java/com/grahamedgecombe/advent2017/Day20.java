package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.MoreObjects;

public final class Day20 {
	private static final Pattern PATTERN = Pattern.compile("^p=<([0-9-]+),([0-9-]+),([0-9-]+)>, v=<([0-9-]+),([0-9-]+),([0-9-]+)>, a=<([0-9-]+),([0-9-]+),([0-9-]+)>$");

	public static final class Vector {
		public static final Vector ORIGIN = new Vector(0, 0, 0);

		private final int x, y, z;

		public Vector(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public int distance(Vector o) {
			return Math.abs(x - o.x) + Math.abs(y - o.y) + Math.abs(z - o.z);
		}

		public int magnitude() {
			return distance(ORIGIN);
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ", " + z + ")";
		}
	}

	public static final class Particle implements Comparable<Particle> {
		public static List<Particle> parse(List<String> lines) {
			List<Particle> particles = new ArrayList<>();

			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);

				Matcher matcher = PATTERN.matcher(line);
				if (!matcher.matches()) {
					throw new IllegalArgumentException();
				}

				Vector position = new Vector(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
				Vector velocity = new Vector(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
				Vector acceleration = new Vector(Integer.parseInt(matcher.group(7)), Integer.parseInt(matcher.group(8)), Integer.parseInt(matcher.group(9)));

				particles.add(new Particle(i, position, velocity, acceleration));
			}

			return particles;
		}

		private final int id;
		private final Vector position, velocity, acceleration;

		public Particle(int id, Vector position, Vector velocity, Vector acceleration) {
			this.id = id;
			this.position = position;
			this.velocity = velocity;
			this.acceleration = acceleration;
		}

		@Override
		public int compareTo(Particle o) {
			int cmp = acceleration.magnitude() - o.acceleration.magnitude();
			if (cmp != 0) {
				return cmp;
			}

			cmp = velocity.magnitude() - o.velocity.magnitude();
			if (cmp != 0) {
				return cmp;
			}

			return position.magnitude() - o.position.magnitude();
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("position", position)
				.add("velocity", velocity)
				.add("acceleration", acceleration)
				.toString();
		}
	}

	public static int getClosestToOrigin(List<Particle> particles) {
		Collections.sort(particles);
		return particles.get(0).id;
	}

	public static void main(String[] args) throws IOException {
		List<Particle> particles = Particle.parse(AdventUtils.readLines("day20.txt"));
		System.out.println(getClosestToOrigin(particles));
	}
}
