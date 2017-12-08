package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day8 {
	private static final Pattern PATTERN = Pattern.compile("^([a-z]+) (inc|dec) (-?[0-9]+) if ([a-z]+) (==|!=|>=|>|<=|<) (-?[0-9]+)$");

	public enum Condition {
		EQ {
			@Override
			public boolean evaluate(int operand1, int operand2) {
				return operand1 == operand2;
			}
		},
		NE {
			@Override
			public boolean evaluate(int operand1, int operand2) {
				return operand1 != operand2;
			}
		},
		GT {
			@Override
			public boolean evaluate(int operand1, int operand2) {
				return operand1 > operand2;
			}
		},
		GTE {
			@Override
			public boolean evaluate(int operand1, int operand2) {
				return operand1 >= operand2;
			}
		},
		LT {
			@Override
			public boolean evaluate(int operand1, int operand2) {
				return operand1 < operand2;
			}
		},
		LTE {
			@Override
			public boolean evaluate(int operand1, int operand2) {
				return operand1 <= operand2;
			}
		};

		public static Condition parse(String str) {
			switch (str) {
				case "==":
					return EQ;
				case "!=":
					return NE;
				case ">":
					return GT;
				case ">=":
					return GTE;
				case "<":
					return LT;
				case "<=":
					return LTE;
			}

			throw new IllegalArgumentException();
		}

		public abstract boolean evaluate(int operand1, int operand2);
	}

	public static final class Instruction {
		private final String register;
		private final boolean increment;
		private final int value;
		private final Condition condition;
		private final String conditionRegister;
		private final int conditionValue;

		public Instruction(String register, boolean increment, int value, Condition condition, String conditionRegister, int conditionValue) {
			this.register = register;
			this.increment = increment;
			this.value = value;
			this.condition = condition;
			this.conditionRegister = conditionRegister;
			this.conditionValue = conditionValue;
		}

		public void evaluate(Map<String, Integer> registers) {
			if (condition.evaluate(registers.getOrDefault(conditionRegister, 0), conditionValue)) {
				int v = registers.getOrDefault(register, 0);
				if (increment) {
					v += value;
				} else {
					v -= value;
				}
				registers.put(register, v);
			}
		}
	}

	public static final class Machine {
		private final List<Instruction> instructions;
		private final Map<String, Integer> registers;
		private int maxValue;

		public Machine(List<Instruction> instructions) {
			this.instructions = instructions;
			this.registers = new HashMap<>();
		}

		public void evaluate() {
			registers.clear();

			for (Instruction instruction : instructions) {
				instruction.evaluate(registers);
				maxValue = Math.max(maxValue, registers.getOrDefault(instruction.register, 0));
			}
		}

		public int getMaxFinalRegister() {
			return registers.values()
				.stream()
				.mapToInt(v -> v)
				.max()
				.orElseThrow(IllegalStateException::new);
		}

		public int getMaxIntermediateRegister() {
			return maxValue;
		}
	}

	public static void main(String[] args) throws IOException {
		Machine instructions = readInstructions(AdventUtils.readLines("day8.txt"));
		instructions.evaluate();
		System.out.println(instructions.getMaxFinalRegister());

		instructions.evaluate();
		System.out.println(instructions.getMaxIntermediateRegister());
	}

	public static Machine readInstructions(List<String> lines) {
		List<Instruction> instructions = new ArrayList<>();

		for (String line : lines) {
			Matcher matcher = PATTERN.matcher(line);
			if (!matcher.matches()) {
				throw new IllegalArgumentException();
			}

			String register = matcher.group(1);
			boolean increment = matcher.group(2).equals("inc");
			int value = Integer.parseInt(matcher.group(3));
			String conditionRegister = matcher.group(4);
			Condition condition = Condition.parse(matcher.group(5));
			int conditionValue = Integer.parseInt(matcher.group(6));

			instructions.add(new Instruction(register, increment, value, condition, conditionRegister, conditionValue));
		}

		return new Machine(instructions);
	}
}
