package com.rosteach.statemachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;

@Configuration
public class AdSMServiceConfig {
	@Autowired
	@Qualifier("sm_ad")
	private StateMachineFactory<States, Events> stateMachineFactory;
	
	@Bean(name="sm_ad_service")
	public StateMachineService<States, Events> stateMachineService(
			StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersister) {
		return new DefaultStateMachineService<States, Events>(stateMachineFactory, stateMachineRuntimePersister);
	}
}
