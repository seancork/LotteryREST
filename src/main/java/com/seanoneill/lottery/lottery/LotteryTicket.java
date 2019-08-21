package com.seanoneill.lottery.lottery;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LotteryTicket {
	 //one ticket to many ticket lines
    @OneToMany(targetEntity=TicketLine.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<TicketLine> lines;

    private boolean isChecked = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public LotteryTicket(){}

    public LotteryTicket(long id) {
        this.id = id;
    }

    public LotteryTicket(List<TicketLine> lines) {
        this.lines = lines;
    }

   //add line to ticket
    public void addLine(TicketLine line){
        if(lines == null){
            this.lines = new ArrayList<TicketLine>();
        }
        this.lines.add(line);
    }

    //get ticket id
    public long getTicketId() {
        return id;
    }

    //get lines linked to ticket
    public List<TicketLine> getTicketLines() {
        return lines;
    }

      //mark ticket as true
    public void checkTicket(){
        isChecked = true;
    }

     // check to see if ticket is checked
    public boolean getChecked() {
        return isChecked;
    }
}
