package com.rosteach.statemachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import com.rosteach.statemachine.config.enums.AdEvent;
import com.rosteach.statemachine.config.enums.AdState;
import com.rosteach.statemachine.config.enums.OrderEvent;
import com.rosteach.statemachine.config.enums.OrderState;
import com.rosteach.statemachine.repository.SMRepository;

@Configuration
public class JpaPersisterConfig {

	@Bean
	public StateMachineRuntimePersister<OrderState, OrderEvent, String> orderSMRuntimePersister(SMRepository smRepo) {
		return new JpaPersistingStateMachineInterceptor<>(smRepo);
	}

	@Bean
	public StateMachineRuntimePersister<AdState, AdEvent, String> adSMRuntimePersister(SMRepository smRepo) {
		return new JpaPersistingStateMachineInterceptor<>(smRepo);
	}
}
