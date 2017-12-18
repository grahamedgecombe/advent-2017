package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
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

	private static final class Instruction {
		private final Opcode opcode;
		private final int operand1, operand2;
		private final boolean immediate;

		public Instruction(Opcode opcode, int operand1, int operand2, boolean immediate) {
			this.opcode = opcode;
			this.operand1 = operand1;
			this.operand2 = operand2;
			this.immediate = immediate;
		}
	}

	public static final class Machine {
		public static Machine create(List<String> lines) {
			List<Instruction> instructions = new ArrayList<>();

			for (String line : lines) {
				Matcher matcher = PATTERN.matcher(line);
				if (!matcher.matches()) {
					throw new IllegalStateException();
				}

				Opcode opcode = Opcode.valueOf(matcher.group(1).toUpperCase());

				String str1 = matcher.group(2);
				String str2 = matcher.group(3);

				int operand1 = str1.charAt(0) - 'a';

				int operand2 = 0;
				boolean immediate = false;

				if (str2 != null) {
					try {
						operand2 = Integer.parseInt(str2);
						immediate = true;
					} catch (NumberFormatException ex) {
						operand2 = str2.charAt(0) - 'a';
					}
				}

				instructions.add(new Instruction(opcode, operand1, operand2, immediate));
			}

			return new Machine(instructions);
		}

		private final List<Instruction> instructions;
		private long[] registers = new long[26];
		private int pc;
		private long freq;

		private Machine(List<Instruction> instructions) {
			this.instructions = instructions;
		}

		public long run() {
			while (pc >= 0 && pc < instructions.size()) {
				Instruction instruction = instructions.get(pc);
				long operand2 = instruction.immediate ? instruction.operand2 : registers[instruction.operand2];

				switch (instruction.opcode) {
					case SND:
						freq = registers[instruction.operand1];
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
						if (registers[instruction.operand1] != 0) {
							return freq;
						}
						break;
					case JGZ:
						if (registers[instruction.operand1] > 0) {
							pc += operand2 - 1;
						}
						break;
					default:
						throw new IllegalStateException();
				}

				pc++;
			}

			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		Day18.Machine machine = Machine.create(AdventUtils.readLines("day18.txt"));
		System.out.println(machine.run());
	}
}
