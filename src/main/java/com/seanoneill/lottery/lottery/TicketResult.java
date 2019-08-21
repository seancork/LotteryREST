package com.seanoneill.lottery.lottery;

import java.util.*;
import java.util.stream.Collectors;

public class TicketResult {
     List<LineResult> results = new ArrayList<LineResult>();
    public TicketResult(LotteryTicket ticket) {
        for (TicketLine lotteryLine : ticket.getTicketLines()) {
            results.add(new LineResult(lotteryLine));
        }
    }
    
    //Highest to lowest values
    public List<LineResult> getTicketResults() {
        return results.stream().sorted(Comparator.comparingInt(LineResult::getLineResult).reversed()).collect(Collectors.toList());
    }}
