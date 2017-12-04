package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public final class Day4 {
	public static void main(String[] args) throws IOException {
		List<String> passphrases = AdventUtils.readLines("day4.txt");
		System.out.println(passphrases.stream().filter(Day4::isValidPart1).count());
		System.out.println(passphrases.stream().filter(Day4::isValidPart2).count());
	}

	public static boolean isValidPart1(String passphrase) {
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

	public static boolean isValidPart2(String passphrase) {
		String[] words = passphrase.split(" ");
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < i; j++) {
				if (isAnagram(words[i], words[j])) {
					return false;
				}
			}
		}

		return true;
	}

	private static boolean isAnagram(String a, String b) {
		List<Integer> sortedA = a.chars().sorted().boxed().collect(Collectors.toList());
		List<Integer> sortedB = b.chars().sorted().boxed().collect(Collectors.toList());
		return sortedA.equals(sortedB);
	}
}
