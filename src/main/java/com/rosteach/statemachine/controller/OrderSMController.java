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

import com.rosteach.statemachine.config.enums.OrderEvent;
import com.rosteach.statemachine.config.enums.OrderState;
import com.rosteach.statemachine.controller.projection.SMProjection;
import com.rosteach.statemachine.repository.SMRepository;
import com.rosteach.statemachine.service.OrderSMService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-sm")
public class OrderSMController {

	private final OrderSMService service;
	private final SMRepository repo;

	@GetMapping()
	public Iterable<JpaRepositoryStateMachine> get() throws Exception {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<StateMachineContext<OrderState, OrderEvent>> get(@PathVariable String id) throws Exception {
		return Optional.ofNullable(service.getSMContext(id)).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping
	public ResponseEntity<StateMachineContext<OrderState, OrderEvent>> updateState(
			@RequestBody SMProjection projection) throws Exception {
		return ResponseEntity.ok(service.updateState(projection.getMachineId(), projection.getOrderEvent()));
	}

}
