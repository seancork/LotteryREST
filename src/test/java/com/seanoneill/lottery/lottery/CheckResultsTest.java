package com.seanoneill.lottery.lottery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes =LotteryApplication.class)
//checking line results
public class CheckResultsTest{
	
	  @Test
	  //If the sum of the line is two, result is 10
	  public void checkIfSumIsTwo() {
		       	int[] test = {1,1,0};
		        TicketLine lotteryLine = new TicketLine (test);
		            assertEquals(lotteryLine.checkLine(), 10);
	  }     
	  
	  @Test
	  //if all line nubmers are equal, result is 5
	  public void checkIfAllNumbersEqual() {
		       	int[] test = {1,1,1};
		        TicketLine lotteryLine = new TicketLine (test);
		            assertEquals(lotteryLine.checkLine(), 5);
	  }     
	  
	  @Test
	  //if first nubmer is differnt from 2nd & 3rd, result is 1
	  public void checkIfFirstNumberIsDifferntFrom2ndAnd3rd() {
		       	int[] test = {2,1,3};
		        TicketLine lotteryLine = new TicketLine (test);
		            assertEquals(lotteryLine.checkLine(), 1);
	  }  
	  
	  @Test
	  //check if result is 0
	  public void checkResultisZero() {
		       	int[] test = {1,1,3};
		        TicketLine lotteryLine = new TicketLine (test);
		            assertEquals(lotteryLine.checkLine(), 0);
	  }   
}
