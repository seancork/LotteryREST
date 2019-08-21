package com.seanoneill.lottery.lottery;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seanoneill.lottery.lottery.exceptions.GeneralCustomException;

@RestController
public class LotteryController {

    @Autowired
    CreateTicketLines CreateTicketLines;

    @Autowired
    TicketRepository lotteryTicketRepository;
    
    //Default homepage for lottery game
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String indexHomepage() {
        return "This is a simple REST interface for a lottery system.";
    }

    // Create ticket
    // Example:http://localhost:8080/ticket?lines=3
    @RequestMapping(value = "/ticket",method = RequestMethod.POST)
    public LotteryTicket createLotteryTicket(@RequestParam(value="lines") Integer lines) {
    	  if(lines == null) {
       	   throw new GeneralCustomException("Lines param can't be null");
          }
        LotteryTicket lotteryTicket = CreateTicketLines.generateTicket(lines);
        lotteryTicketRepository.save(lotteryTicket);
        return lotteryTicket;
    }
    
 // get All tickets
    // Example: http://localhost:8080/ticket
    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
    public Iterable<LotteryTicket> getAllTicket() {
        Iterable<LotteryTicket> lotteryTicket = lotteryTicketRepository.findAll();
          //check to see if there is any tickets, if not throw exception
        	  if(((Collection<?>)lotteryTicket).size() == 0) {
        			 throw new GeneralCustomException("There currerntly is no tickets :(");
        	  }
      
        return lotteryTicket;
    }
    
    // get single  ticket by id
    // Example: http://localhost:8080/ticket/1
    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
    public LotteryTicket getSingleTicket(@PathVariable Long id){
    	  if(id == null) {
       	   throw new GeneralCustomException("id can't be null");
          }
        LotteryTicket lotteryTicket = lotteryTicketRepository.findAllById(id);
        if( lotteryTicket == null) {
        	 throw new GeneralCustomException("This ticket does not exist");
        }
      
        return lotteryTicket;
    }

    // update ticket with more lines if ticket has not already been checked.
    // Example: http://localhost:8080/ticket/1?lines=1
    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.PUT)
    public LotteryTicket addLineToTicket(@PathVariable Long id, @RequestParam Integer lines) {
    	  if(id == null) {
       	   throw new GeneralCustomException("id can't be null");
          }
        LotteryTicket lotteryTicket = lotteryTicketRepository.findAllById(id);
        if( lotteryTicket == null) {
       	 throw new GeneralCustomException("This ticket does not exist");
       }
     
        if(!lotteryTicket.getChecked()){
            for(int i=0;i<lines;i++){
                lotteryTicket.addLine(CreateTicketLines.createTicketLine());
            }}else{  
            throw new GeneralCustomException("Can't add line(s) because status was already checked.");
        }
        lotteryTicketRepository.save(lotteryTicket);
        return lotteryTicket;
    }

  //get results for each line in ticket & set checked to true.
  // Example:  http://localhost:8080/status?id=1
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public TicketResult checkTicket(@RequestParam(value="id") Long id) {
       if(id == null) {
    	   throw new GeneralCustomException("id can't be null");
       }
        LotteryTicket lotteryTicket = lotteryTicketRepository.findAllById(id);
        if( lotteryTicket == null) {
          	 throw new GeneralCustomException("This ticket does not exist");
          }
        lotteryTicket.checkTicket();
        lotteryTicketRepository.save(lotteryTicket);
        TicketResult ticketResult = new TicketResult(lotteryTicket);
        return ticketResult;
    }
}
