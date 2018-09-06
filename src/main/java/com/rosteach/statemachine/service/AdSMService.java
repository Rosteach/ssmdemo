package com.rosteach.statemachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;

import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdSMService {
	@Autowired
	@Qualifier("sm_order_service")
	private StateMachineService<States, Events> service;
	private final StateMachinePersist<States, Events, String> persist;
}
