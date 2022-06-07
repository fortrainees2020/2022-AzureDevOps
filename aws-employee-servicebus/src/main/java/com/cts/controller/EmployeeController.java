package com.cts.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.cts.model.Employee;

import ch.qos.logback.classic.Logger;

@RestController
//@RequestMapping("/api/emp")
public class EmployeeController {
	 private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeController.class);
	 
	 static String connectionString = "Endpoint=sb://demo-service-bus-10.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=pfwZSjeqMLfveUpIK2Ejx+pTo55KAMydV+djToVcH3A=;EntityPath=employeequeue";
	 static String queueName = "employeequeue";
	 
	@RequestMapping("/employees")
	 public List<Employee> getEmployees() {
		 List<Employee> empList= new ArrayList<Employee>();
		 empList.add(new Employee(10, 100,"Robert3"));
		 empList.add(new Employee(10,200,"Albert3"));
		 empList.add(new Employee(20,300,"Gini3"));
		 
		 LOGGER.trace("doStuff needed more information - {}");
	        LOGGER.debug("doStuff needed to debug - {}");
	        LOGGER.info("doStuff took input - {}");
	        LOGGER.warn("doStuff needed to warn - {}");
	        LOGGER.error("doStuff encountered an error with value - {}");
		 
		 return empList; 
}
	
	@RequestMapping("/sendMessage")
	 public void sendMessageEmployees() {
		// create a Service Bus Sender client for the queue 
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionString)
	            .sender()
	            .queueName(queueName)
	            .buildClient();

	    // send one message to the queue
	   // senderClient.sendMessage(new ServiceBusMessage("Hello, World!"));
	    senderClient.sendMessage(new ServiceBusMessage(new Employee(10, 100,"Robert3").getEname()));
	   
	    System.out.println("Sent a single message to the queue: " + queueName);       
	}
}
	