 package com.seanoneill.lottery.lottery;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class CreateTicketLines {   
	//generate random line of 3 numbers between 0 & 3
    public TicketLine createTicketLine() {
    	//Generate int stream from the next int from a random number generator, limt size  & covert to array.
        return new TicketLine(IntStream.generate(() -> new Random().nextInt(3)).limit(3).toArray());
    }
    
    //create ticket based on nubmer of lines
    public LotteryTicket generateTicket(int numberOfLines){
        List<TicketLine> ticketLines = new ArrayList<TicketLine>(numberOfLines);
        for(int i=0;i<numberOfLines;i++){
            ticketLines.add(createTicketLine());
        }
        return new LotteryTicket(ticketLines);
    }}
