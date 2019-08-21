package com.seanoneill.lottery.lottery;

public class LineResult {
	private TicketLine lotteryLine;
	private Integer lineResult;

	public LineResult(TicketLine line) {
		lotteryLine = line;
		lineResult = lotteryLine.checkLine();
	}

	public TicketLine getLotteryLine() {
		return lotteryLine;
	}

	public Integer getLineResult() {
		return lineResult;
	}
}
