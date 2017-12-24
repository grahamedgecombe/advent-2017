package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day23 {
	private static final Pattern PATTERN = Pattern.compile("^(set|sub|mul|jnz) ([a-z]|(?:[0-9-]+))(?: ([a-z]|(?:[0-9-]+)))?$");

	private enum Opcode {
		SET,
		SUB,
		MUL,
		JNZ
	}

	private static final class Instruction {
		private final Opcode opcode;
		private final int operand1, operand2;
		private final boolean immediate1, immediate2;

		private Instruction(Opcode opcode, int operand1, int operand2, boolean immediate1, boolean immediate2) {
			this.opcode = opcode;
			this.operand1 = operand1;
			this.operand2 = operand2;
			this.immediate1 = immediate1;
			this.immediate2 = immediate2;
		}
	}

	private static final class Machine {
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

			return new Machine(instructions);
		}

		private final List<Instruction> instructions;

		private Machine(List<Instruction> instructions) {
			this.instructions = instructions;
		}

		public int run() {
			int pc = 0;
			int multiplications = 0;
			long[] registers = new long[8];

			while (pc >= 0 && pc < instructions.size()) {
				Instruction instruction = instructions.get(pc);

				long operand1 = instruction.immediate1 ? instruction.operand1 : registers[instruction.operand1];
				long operand2 = instruction.immediate2 ? instruction.operand2 : registers[instruction.operand2];

				switch (instruction.opcode) {
					case SET:
						registers[instruction.operand1] = operand2;
						break;
					case SUB:
						registers[instruction.operand1] -= operand2;
						break;
					case MUL:
						registers[instruction.operand1] *= operand2;
						multiplications++;
						break;
					case JNZ:
						if (operand1 != 0) {
							pc += operand2 - 1;
						}
						break;
					default:
						throw new IllegalStateException();
				}

				pc++;
			}

			return multiplications;
		}
	}

	public static void main(String[] args) throws IOException {
		Machine machine = Machine.create(AdventUtils.readLines("day23.txt"));
		System.out.println(machine.run());
	}
}
