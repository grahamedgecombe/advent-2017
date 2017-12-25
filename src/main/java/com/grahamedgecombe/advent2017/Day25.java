package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day25 {
	private static final Pattern INITIAL_STATE_PATTERN = Pattern.compile("^Begin in state (.)\\.$");
	private static final Pattern STEPS_PATTERN = Pattern.compile("^Perform a diagnostic checksum after ([0-9]+) steps.$");
	private static final Pattern STATE_PATTERN = Pattern.compile("^In state (.):$");
	private static final Pattern VALUE_PATTERN = Pattern.compile("^  If the current value is ([01]):$");
	private static final Pattern WRITE_PATTERN = Pattern.compile("^    - Write the value ([01])\\.$");
	private static final Pattern DIRECTION_PATTERN = Pattern.compile("^    - Move one slot to the (left|right)\\.$");
	private static final Pattern NEXT_STATE_PATTERN = Pattern.compile("^    - Continue with state (.)\\.$");

	public static final class StateValue {
		private final String state;
		private final boolean value;

		public StateValue(String state, boolean value) {
			this.state = state;
			this.value = value;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			StateValue that = (StateValue) o;
			return value == that.value &&
				Objects.equals(state, that.state);
		}

		@Override
		public int hashCode() {

			return Objects.hash(state, value);
		}
	}

	public static final class Transition {
		private final String state;
		private final boolean value, left;

		public Transition(String state, boolean value, boolean left) {
			this.state = state;
			this.value = value;
			this.left = left;
		}
	}

	public static final class TuringMachine {
		public static TuringMachine parse(List<String> lines) {
			String initialState = null;
			int steps = -1;
			Map<StateValue, Transition> transitions = new HashMap<>();

			String state = null;
			boolean value = false;
			boolean nextValue = false;
			boolean left = false;

			for (String line : lines) {
				if (line.isEmpty()) {
					continue;
				}

				Matcher matcher = INITIAL_STATE_PATTERN.matcher(line);
				if (matcher.matches()) {
					initialState = matcher.group(1);
					continue;
				}

				matcher = STEPS_PATTERN.matcher(line);
				if (matcher.matches()) {
					steps = Integer.parseInt(matcher.group(1));
					continue;
				}

				matcher = STATE_PATTERN.matcher(line);
				if (matcher.matches()) {
					state = matcher.group(1);
					continue;
				}

				matcher = VALUE_PATTERN.matcher(line);
				if (matcher.matches()) {
					value = matcher.group(1).equals("1");
					continue;
				}

				matcher = WRITE_PATTERN.matcher(line);
				if (matcher.matches()) {
					nextValue = matcher.group(1).equals("1");
					continue;
				}

				matcher = DIRECTION_PATTERN.matcher(line);
				if (matcher.matches()) {
					left = matcher.group(1).equals("left");
					continue;
				}

				matcher = NEXT_STATE_PATTERN.matcher(line);
				if (matcher.matches()) {
					String nextState = matcher.group(1);
					transitions.put(new StateValue(state, value), new Transition(nextState, nextValue, left));
					continue;
				}

				throw new IllegalArgumentException(line);
			}

			return new TuringMachine(initialState, steps, transitions);
		}

		private static int index(int i) {
			if (i < 0) {
				return -i * 2 - 1;
			} else {
				return i * 2;
			}
		}

		private final String initialState;
		private final int steps;
		private final Map<StateValue, Transition> transitions;
		private final BitSet tape = new BitSet();

		private TuringMachine(String initialState, int steps, Map<StateValue, Transition> transitions) {
			this.initialState = initialState;
			this.steps = steps;
			this.transitions = transitions;
		}

		public int run() {
			String state = initialState;
			int tapeHead = 0;
			tape.clear();

			for (int i = 0; i < steps; i++) {
				int index = index(tapeHead);
				boolean value = tape.get(index);

				Transition transition = transitions.get(new StateValue(state, value));

				tape.set(index, transition.value);
				state = transition.state;
				tapeHead += transition.left ? -1 : 1;
			}

			return tape.cardinality();
		}
	}

	public static void main(String[] args) throws IOException {
		TuringMachine machine = TuringMachine.parse(AdventUtils.readLines("day25.txt"));
		System.out.println(machine.run());
	}
}
