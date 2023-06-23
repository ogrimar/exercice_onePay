package training.onepay.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Transaction>> getAll() {
		return new ResponseEntity<List<Transaction>>(service.getAllTransactions(), HttpStatusCode.valueOf(200));
	}
	
	@GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> getById(@PathVariable("id") UUID id) {
		return new ResponseEntity<Transaction>(service.getTransaction(id), HttpStatusCode.valueOf(200));
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction t) {
		return new ResponseEntity<Transaction>(service.createTransaction(t), HttpStatusCode.valueOf(201));
	}

	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction t) {
		return new ResponseEntity<Transaction>(service.updateTransaction(t), HttpStatusCode.valueOf(201));
	}
}
