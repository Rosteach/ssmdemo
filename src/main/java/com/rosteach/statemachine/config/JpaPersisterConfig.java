package com.rosteach.statemachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;
import com.rosteach.statemachine.repository.SMRepository;

@Configuration
public class JpaPersisterConfig {

	@Bean
	public StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersister(SMRepository smRepo) {
		return new JpaPersistingStateMachineInterceptor<>(smRepo);
	}
}
