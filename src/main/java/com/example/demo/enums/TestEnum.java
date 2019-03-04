package com.example.demo.enums;

public enum TestEnum {
	ZZX(1), LBJ(2), EEE(3), WWW(4);

	private int value;

	private TestEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
