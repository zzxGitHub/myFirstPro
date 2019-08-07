package com.example.demo.consts;
/**
 * 业务类型枚举
 * @author zzx
 *
 */
public enum BusinessFromEnum {
	/**
	 * 重点人0
	 */
	IMPORT_PERSON(0),
	/**
	 * 警情1
	 */
	ALARM(1),
	/**
	 * (潍坊)情报:2
	 */
	MONITOR_WF(2),
	/**
	 * 武汉情报：66
	 */
	PUBLIC_SENTIMENT(66),
	/**
	 * (潍坊)案件：5
	 */
	CASE_INFO_WF(5),
	/**
	 * 线索：6
	 */
	CLUE(6),
	/**
	 * 情报预警：8
	 */
	INTELLIGENCE_WARNING(8),
	/**
	 * 工作指令：9
	 */
	WORK_INSTRUCTION(9),
	/**
	 * 重点对象：10
	 */
	IMPORT_OBJECT(10),
	/**
	 * 情报：16
	 */
	INTELLIGENCE(16),
	/**
	 * (潍坊)网情：99
	 */
	NET_ALARM_WF(99);

	private int value;

	private BusinessFromEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
