package com.seanoneill.lottery.lottery;


import java.util.stream.IntStream;
import javax.persistence.*;

@Entity
public class TicketLine {
    
	public TicketLine(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lineId;

    private int[] lineNumbers = new int[3];

	public TicketLine(int[] nums){
		for(int i = 0; i < nums.length; i++) {
        lineNumbers[i] = nums[i];
    }}

    public int[] getLineNumbers() {
        return lineNumbers;
    }

 // below is to find out the result of lottery line.
    public int checkLine(){
    	 if(IntStream.of(lineNumbers).sum() == 2) {
      		  return 10;
      	  }else if(isAllEqual(lineNumbers) == true) {
      		  return 5;
      	  }else if(lineNumbers[0] != lineNumbers[1] && lineNumbers[0] != lineNumbers[2]){
      		  return 1;
      	  }else {
      		return 0;
      	  }
    }
    
    //check if equal in array, lottery line
    public static boolean isAllEqual(int[] a){
        for(int i=1; i<a.length; i++){
            if(a[0] != a[i]){
                return false;
            }}
        return true;
    }}
