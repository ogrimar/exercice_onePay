package training.onepay.service;

import java.util.List;
import java.util.UUID;

import training.onepay.domain.Transaction;

public interface TransactionService {
	public Transaction getTransaction(UUID id);
	
	public List<Transaction> getAllTransactions();
	
	public Transaction createTransaction(Transaction t);
	
	public Transaction updateTransaction(Transaction t, UUID id);
}
