package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.List;

public final class Day4 {
	public static void main(String[] args) throws IOException {
		List<String> passphrases = AdventUtils.readLines("day4.txt");
		System.out.println(passphrases.stream().filter(Day4::isValid).count());
	}

	public static boolean isValid(String passphrase) {
		String[] words = passphrase.split(" ");
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < i; j++) {
				if (words[i].equals(words[j])) {
					return false;
				}
			}
		}

		return true;
	}
}
