package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day18 {
	public static final Pattern PATTERN = Pattern.compile("^(snd|set|add|mul|mod|rcv|jgz) ([a-z]|(?:[0-9-]+))(?: ([a-z]|(?:[0-9-]+)))?$");

	private enum Opcode {
		SND,
		SET,
		ADD,
		MUL,
		MOD,
		RCV,
		JGZ
	}

	public static final class Instruction {
		private final Opcode opcode;
		private final int operand1, operand2;
		private final boolean immediate1, immediate2;

		public Instruction(Opcode opcode, int operand1, int operand2, boolean immediate1, boolean immediate2) {
			this.opcode = opcode;
			this.operand1 = operand1;
			this.operand2 = operand2;
			this.immediate1 = immediate1;
			this.immediate2 = immediate2;
		}
	}

	public static List<Instruction> parse(List<String> lines) {
		List<Instruction> instructions = new ArrayList<>();

		for (String line : lines) {
			Matcher matcher = PATTERN.matcher(line);
			if (!matcher.matches()) {
				throw new IllegalStateException();
			}

			Opcode opcode = Opcode.valueOf(matcher.group(1).toUpperCase());

			String str1 = matcher.group(2);
			String str2 = matcher.group(3);

			int operand1;
			boolean immediate1 = false;

			try {
				operand1 = Integer.parseInt(str1);
				immediate1 = true;
			} catch (NumberFormatException ex) {
				operand1 = str1.charAt(0) - 'a';
			}

			int operand2 = 0;
			boolean immediate2 = false;

			if (str2 != null) {
				try {
					operand2 = Integer.parseInt(str2);
					immediate2 = true;
				} catch (NumberFormatException ex) {
					operand2 = str2.charAt(0) - 'a';
				}
			}

			instructions.add(new Instruction(opcode, operand1, operand2, immediate1, immediate2));
		}

		return instructions;
	}

	private static final class Machine {
		private final List<Instruction> instructions;
		private final Deque<Long> in, out;
		private long[] registers = new long[26];
		private int pc, sends;

		public Machine(List<Instruction> instructions, Deque<Long> in, Deque<Long> out, int pid) {
			this.instructions = instructions;
			this.in = in;
			this.out = out;
			this.registers['p' - 'a'] = pid;
		}

		private boolean cycle(boolean part1) {
			Instruction instruction = instructions.get(pc);

			long operand1 = instruction.immediate1 ? instruction.operand1 : registers[instruction.operand1];
			long operand2 = instruction.immediate2 ? instruction.operand2 : registers[instruction.operand2];

			switch (instruction.opcode) {
				case SND:
					out.add(operand1);
					sends++;
					break;
				case SET:
					registers[instruction.operand1] = operand2;
					break;
				case ADD:
					registers[instruction.operand1] += operand2;
					break;
				case MUL:
					registers[instruction.operand1] *= operand2;
					break;
				case MOD:
					registers[instruction.operand1] %= operand2;
					break;
				case RCV:
					if (part1) {
						if (operand1 != 0) {
							return false;
						}
					} else {
						if (in.isEmpty()) {
							return false;
						}
						registers[instruction.operand1] = in.poll();
					}
					break;
				case JGZ:
					if (operand1 > 0) {
						pc += operand2 - 1;
					}
					break;
				default:
					throw new IllegalStateException();
			}

			pc++;
			return true;
		}
	}

	public static long runPart1(List<Instruction> instructions) {
		Deque<Long> queue = new ArrayDeque<>();

		Machine machine = new Machine(instructions, queue, queue, 0);
		while (machine.cycle(true));

		return queue.pollLast();
	}

	public static int runPart2(List<Instruction> instructions) {
		Deque<Long> queue0To1 = new ArrayDeque<>();
		Deque<Long> queue1To0 = new ArrayDeque<>();

		Machine machine0 = new Machine(instructions, queue1To0, queue0To1, 0);
		Machine machine1 = new Machine(instructions, queue0To1, queue1To0, 1);
		while (machine0.cycle(false) || machine1.cycle(false));

		return machine1.sends;
	}

	public static void main(String[] args) throws IOException {
		List<Instruction> instructions = parse(AdventUtils.readLines("day18.txt"));
		System.out.println(runPart1(instructions));
		System.out.println(runPart2(instructions));
	}
}
