package com.seanoneill.lottery.lottery;

import org.springframework.data.repository.CrudRepository;

// interface for generic CRUD operations eg  save, findById, delete
public interface TicketRepository extends CrudRepository <LotteryTicket, Long> {
    LotteryTicket findAllById(Long id);
}