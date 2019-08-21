package com.seanoneill.lottery.lottery;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes =LotteryApplication.class)

public class LotteryApplicationTests {
	  MockMvc mockMvc;
	  @Autowired
	    private LotteryController LotteryController;
	  
	   @Mock
	    private CreateTicketLines ticketFactory;
	   @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(LotteryController).build();
	        
	        mockMvc.perform(
		            MockMvcRequestBuilders.post("/ticket")
		                .param("lines", "1"));      
	    }
	    
	@Test
	//check if ticket  is created
	   public void testCreateTicket() throws Exception{
		  MvcResult result = mockMvc.perform(
		            MockMvcRequestBuilders.post("/ticket")
		                .param("lines", "1"))
		            .andExpect(MockMvcResultMatchers.status().is(200))
		            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		            .andExpect(MockMvcResultMatchers.jsonPath("$.checked").value(false))
		            .andReturn();
		   assertNotNull(result);	  
	   }
	
	@Test
	//Get Ticket
	   public void testGetTicket() throws Exception{
		  MvcResult result = mockMvc.perform(
		            MockMvcRequestBuilders.get("/ticket/1"))
		            .andExpect(MockMvcResultMatchers.status().is(200))
		            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		            .andExpect(MockMvcResultMatchers.jsonPath("$.ticketLines").exists())
		            .andReturn();
		    assertNotNull(result);  
	   }
	
	//Get Ticket
	@Test
	   public void testGetAllTicket() throws Exception{
		  MvcResult result = mockMvc.perform(
		            MockMvcRequestBuilders.get("/ticket"))
		            .andExpect(MockMvcResultMatchers.status().is(200))
		            .andExpect(MockMvcResultMatchers.jsonPath("$[*].ticketLines").exists())
		            .andReturn();
		  assertNotNull(result);  	  
	   }
	
	//check if results are returned
	@Test
	   public void testLotteryTicketStatus() throws Exception{
		setUp();
		  MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/status")
				  .param("id", "1"))
				  .andExpect(MockMvcResultMatchers.jsonPath("$.ticketResults[*].lineResult").exists())
			       .andExpect(MockMvcResultMatchers.status().is(200))
			        .andReturn();
		  assertNotNull(result);  
	   }
	}
