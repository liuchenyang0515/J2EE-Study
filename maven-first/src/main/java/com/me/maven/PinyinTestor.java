package com.me.maven;

import java.util.Scanner;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinTestor {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(str.charAt(0));
		for (String py : pinyin) {
			System.out.println(py);
		}
	}
}
