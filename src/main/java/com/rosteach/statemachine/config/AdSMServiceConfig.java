package com.rosteach.statemachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

import com.rosteach.statemachine.config.enums.AdEvent;
import com.rosteach.statemachine.config.enums.AdState;

@Configuration
public class AdSMServiceConfig {
	@Autowired
	@Qualifier("sm_ad")
	private StateMachineFactory<AdState, AdEvent> stateMachineFactory;
	
	@Bean(name="sm_ad_service")
	public StateMachineService<AdState, AdEvent> stateMachineService(
			StateMachineRuntimePersister<AdState, AdEvent, String> stateMachineRuntimePersister) {
		return new DefaultStateMachineService<AdState, AdEvent>(stateMachineFactory, stateMachineRuntimePersister);
	}
}
