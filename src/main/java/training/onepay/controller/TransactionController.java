package training.onepay.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.onepay.domain.Transaction;
import training.onepay.service.TransactionService;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {
	
	TransactionService service;
	
	@Autowired
	public TransactionController(TransactionService service) {
		super();
		this.service = service;
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getAll() {
		return service.getAllTransactions();
	}
	
	@GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction getById(@PathVariable("id") UUID id) {
		return service.getTransaction(id);
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction createTransaction(@RequestBody Transaction t) {
		return service.createTransaction(t);
	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction updateTransaction(@RequestBody Transaction t) {
		return service.updateTransaction(t);
	}
}
