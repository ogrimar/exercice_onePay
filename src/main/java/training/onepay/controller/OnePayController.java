package training.onepay.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import training.onepay.model.Transaction;

@RestController
public class OnePayController {

	@GetMapping(value = "/all")
	public List<Transaction> getAll() {
		return new ArrayList<Transaction>();
	}
}
