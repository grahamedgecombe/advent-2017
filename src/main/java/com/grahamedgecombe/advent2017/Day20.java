package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;

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

	private static final class Collision implements Comparable<Collision> {
		private final Particle p1, p2;
		private final int t;

		public Collision(Particle p1, Particle p2, int t) {
			this.p1 = p1;
			this.p2 = p2;
			this.t = t;
		}

		@Override
		public int compareTo(Collision o) {
			int cmp = t - o.t;
			if (cmp != 0) {
				return cmp;
			}

			cmp = p1.compareTo(o.p1);
			if (cmp != 0) {
				return cmp;
			}

			return p2.compareTo(o.p2);
		}
	}

	public static Set<Integer> solveQuadratic(double a, double b, double c) {
		Set<Integer> xs = new HashSet<>();

		if (a != 0) {
			int x1 = (int) Math.round((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
			int x2 = (int) Math.round((-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a));

			if (a * x1 * x1 + b * x1 + c == 0) {
				xs.add(x1);
			}

			if (a * x2 * x2 + b * x2 + c == 0) {
				xs.add(x2);
			}
		} else if (b != 0) {
			int x = (int) Math.round(-c / b);

			if (b * x + c == 0) {
				xs.add(x);
			}
		}

		return xs;
	}

	public static int getClosestToOrigin(List<Particle> particles) {
		Collections.sort(particles);
		return particles.get(0).id;
	}

	public static int collide(List<Particle> particles) {
		SortedSet<Collision> collisions = new TreeSet<>();

		for (int i = 0; i < particles.size(); i++) {
			for (int j = 0; j < i; j++) {
				Particle p1 = particles.get(i);
				Particle p2 = particles.get(j);

				double ax = (p1.acceleration.x - p2.acceleration.x) / 2D;
				double ay = (p1.acceleration.y - p2.acceleration.y) / 2D;
				double az = (p1.acceleration.z - p2.acceleration.z) / 2D;

				double bx = (p1.velocity.x - p2.velocity.x) + (p1.acceleration.x - p2.acceleration.x) / 2D;
				double by = (p1.velocity.y - p2.velocity.y) + (p1.acceleration.y - p2.acceleration.y) / 2D;
				double bz = (p1.velocity.z - p2.velocity.z) + (p1.acceleration.z - p2.acceleration.z) / 2D;

				double cx = p1.position.x - p2.position.x;
				double cy = p1.position.y - p2.position.y;
				double cz = p1.position.z - p2.position.z;

				Set<Integer> tx = solveQuadratic(ax, bx, cx);
				Set<Integer> ty = solveQuadratic(ay, by, cy);
				Set<Integer> tz = solveQuadratic(az, bz, cz);

				/* hacky way to deal with cases where the particles collide for every value of t */
				if (ax == 0 && bx == 0 && cx == 0) {
					tx.addAll(ty);
					tx.addAll(tz);
				}

				if (ay == 0 && by == 0 && cy == 0) {
					ty.addAll(tx);
					ty.addAll(tz);
				}

				if (az == 0 && bz == 0 && cz == 0) {
					tz.addAll(tx);
					tz.addAll(ty);
				}

				Set<Integer> ts = Sets.intersection(tx, Sets.intersection(ty, tz));

				OptionalInt t = ts.stream()
					.mapToInt(v -> v)
					.min();

				if (t.isPresent()) {
					collisions.add(new Collision(p1, p2, t.getAsInt()));
				}
			}
		}

		Set<Particle> remaining = new HashSet<>(particles);
		Set<Particle> tickCollisions = new HashSet<>();

		int t = 0;
		for (Collision collision : collisions) {
			if (t != collision.t) {
				remaining.removeAll(tickCollisions);
				tickCollisions.clear();
				t = collision.t;
			}

			if (remaining.contains(collision.p1) && remaining.contains(collision.p2)) {
				tickCollisions.add(collision.p1);
				tickCollisions.add(collision.p2);
			}
		}
		remaining.removeAll(tickCollisions);

		return remaining.size();
	}

	public static void main(String[] args) throws IOException {
		List<Particle> particles = Particle.parse(AdventUtils.readLines("day20.txt"));
		System.out.println(getClosestToOrigin(particles));
		System.out.println(collide(particles));
	}
}
