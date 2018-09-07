package com.rosteach.statemachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

import com.rosteach.statemachine.config.enums.OrderEvent;
import com.rosteach.statemachine.config.enums.OrderState;

@Configuration
public class OrderSMServiceConfig {
	@Autowired
	@Qualifier("sm_order")
	private StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;
	
	@Bean(name="sm_order_service")
	public StateMachineService<OrderState, OrderEvent> stateMachineService(
			StateMachineRuntimePersister<OrderState, OrderEvent, String> stateMachineRuntimePersister) {
		return new DefaultStateMachineService<OrderState, OrderEvent>(stateMachineFactory, stateMachineRuntimePersister);
	}
}
