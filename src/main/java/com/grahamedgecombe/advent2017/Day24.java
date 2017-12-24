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
		return getStrongestBridge(ports, 0);
	}

	private static int getStrongestBridge(List<Port> ports, int pins) {
		int maxStrength = 0;

		for (int i = 0; i < ports.size(); i++) {
			Port port = ports.get(i);
			if (port.pins1 != pins && port.pins2 != pins) {
				continue;
			}

			List<Port> nextPorts = take(ports, i);
			int nextPins = port.pins1 == pins ? port.pins2 : port.pins1;
			int strength = getStrongestBridge(nextPorts, nextPins) + port.pins1 + port.pins2;

			maxStrength = Math.max(maxStrength, strength);
		}

		return maxStrength;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getStrongestBridge(Port.parse(AdventUtils.readLines("day24.txt"))));
	}
}
