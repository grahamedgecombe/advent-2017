package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day7 {
	private static final Pattern PATTERN = Pattern.compile("^([a-z]+) \\(([0-9]+)\\)(?: -> ((?:[a-z]+, )*[a-z]+))?$");
	private static final String[] EMPTY_STRING_ARRAY = new String[0];

	public static final class Program {
		private final String name;
		private final int weight;
		private final String[] childNames;
		private final List<Program> children = new ArrayList<>();
		private Program parent;

		public Program(String name, int weight, String[] childNames) {
			this.name = name;
			this.weight = weight;
			this.childNames = childNames;
		}

		public String getName() {
			return name;
		}

		public int getWeight() {
			return weight;
		}

		public List<Program> getChildren() {
			return children;
		}

		public Program getParent() {
			return parent;
		}
	}

	public static void main(String[] args) throws IOException {
		Program root = readDiscs(AdventUtils.readLines("day7.txt"));
		System.out.println(root.getName());
	}

	public static Program readDiscs(List<String> lines) {
		Map<String, Program> programs = new HashMap<>();

		for (String line : lines) {
			Matcher matcher = PATTERN.matcher(line);
			if (!matcher.matches()) {
				throw new IllegalArgumentException();
			}

			String name = matcher.group(1);
			int weight = Integer.parseInt(matcher.group(2));
			String[] childNames;

			String group3 = matcher.group(3);
			if (group3 != null) {
				childNames = group3.split(", ");
			} else {
				childNames = EMPTY_STRING_ARRAY;
			}

			programs.put(name, new Program(name, weight, childNames));
		}

		for (Program parent : programs.values()) {
			for (String childName : parent.childNames) {
				Program child = programs.get(childName);
				child.parent = parent;
				parent.children.add(child);
			}
		}

		for (Program program : programs.values()) {
			if (program.parent == null) {
				return program;
			}
		}

		throw new IllegalArgumentException();
	}
}
