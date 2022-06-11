package com.management.system;

import com.management.system.API.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SystemApplicationTests {

	@Autowired
	private BillController billController;

	@Autowired
	private CheckoutController checkoutController;

	@Autowired
	private EventController eventController;

	@Autowired
	private TicketController ticketController;

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {

	}

}
