package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Day24 {
	public static final class Port {
		public static List<Port> parse(List<String> lines) {
			List<Port> ports = new ArrayList<>();

			for (String line : lines) {
				String[] parts = line.split("/");
				if (parts.length != 2) {
					throw new IllegalArgumentException();
				}

				int pins1 = Integer.parseInt(parts[0]);
				int pins2 = Integer.parseInt(parts[1]);
				ports.add(new Port(pins1, pins2));
			}

			return ports;
		}

		private final int pins1, pins2;

		private Port(int pins1, int pins2) {
			this.pins1 = pins1;
			this.pins2 = pins2;
		}

		@Override
		public String toString() {
			return pins1 + "/" + pins2;
		}
	}

	private static List<Port> take(List<Port> ports, int index) {
		List<Port> remainingPorts = new ArrayList<>(ports.size() - 1);
		remainingPorts.addAll(ports.subList(0, index));
		remainingPorts.addAll(ports.subList(index + 1, ports.size()));
		return remainingPorts;
	}

	public static int getStrongestBridge(List<Port> ports) {
		return getBridge(ports, 0, true).strength;
	}

	public static int getLongestBridgeStrength(List<Port> ports) {
		return getBridge(ports, 0, false).strength;
	}

	private static class Bridge {
		private static final Bridge EMPTY = new Bridge(0, 0);

		public static Bridge best(Bridge b1, Bridge b2, boolean part1) {
			if (part1) {
				if (b1.strength >= b2.strength) {
					return b1;
				} else {
					return b2;
				}
			} else {
				if (b1.length > b2.length) {
					return b1;
				} else if (b2.length > b1.length) {
					return b2;
				} else {
					return best(b1, b2, true);
				}
			}
		}

		private final int length, strength;

		private Bridge(int length, int strength) {
			this.length = length;
			this.strength = strength;
		}

		public Bridge extend(Port port) {
			return new Bridge(length + 1, strength + port.pins1 + port.pins2);
		}
	}

	private static Bridge getBridge(List<Port> ports, int pins, boolean part1) {
		Bridge best = Bridge.EMPTY;

		for (int i = 0; i < ports.size(); i++) {
			Port port = ports.get(i);
			if (port.pins1 != pins && port.pins2 != pins) {
				continue;
			}

			List<Port> nextPorts = take(ports, i);
			int nextPins = port.pins1 == pins ? port.pins2 : port.pins1;

			Bridge bridge = getBridge(nextPorts, nextPins, part1).extend(port);

			if (best == null) {
				best = bridge;
			} else {
				best = Bridge.best(best, bridge, part1);
			}
		}

		return best;
	}

	public static void main(String[] args) throws IOException {
		List<Port> ports = Port.parse(AdventUtils.readLines("day24.txt"));
		System.out.println(getStrongestBridge(ports));
		System.out.println(getLongestBridgeStrength(ports));
	}
}
