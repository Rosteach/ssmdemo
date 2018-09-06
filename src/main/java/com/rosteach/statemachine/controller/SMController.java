package com.rosteach.statemachine.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.data.jpa.JpaRepositoryStateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rosteach.statemachine.config.enums.Events;
import com.rosteach.statemachine.config.enums.States;
import com.rosteach.statemachine.controller.projection.EventProjection;
import com.rosteach.statemachine.repository.SMRepository;
import com.rosteach.statemachine.service.OrderSMService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sm")
public class SMController {

	private final OrderSMService smService;
	private final SMRepository smRepo;

	@GetMapping()
	public Iterable<JpaRepositoryStateMachine> get() throws Exception {
		return smRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StateMachineContext<States, Events>> get(@PathVariable String id) throws Exception {
		return Optional.ofNullable(smService.get(id)).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping
	public ResponseEntity<StateMachineContext<States, Events>> updateState(@RequestBody EventProjection projection)
			throws Exception {
		return ResponseEntity.ok(smService.updateState(projection));
	}

}
