package training.onepay.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import training.onepay.domain.Transaction;
import training.onepay.mapper.TransactionMapper;
import training.onepay.model.TransactionModel;
import training.onepay.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	TransactionRepository repository;

	@Autowired
	public TransactionServiceImpl(TransactionRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Transaction getTransaction(UUID id) {
		Optional<TransactionModel> res = repository.findById(id);
		if (res.isEmpty()) {
			return null;
		} else {
			return TransactionMapper.toModel(repository.findById(id).get());
		}
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return repository.findAll().stream().map(o -> TransactionMapper.toModel(o)).toList();
	}

	@Override
	public Transaction createTransaction(Transaction t) {
		TransactionModel transaction = TransactionMapper.toDomain(t);
		return TransactionMapper.toModel(repository.save(transaction));
	}

	@Override
	public Transaction updateTransaction(Transaction t, UUID id) {
		TransactionModel transaction = TransactionMapper.toDomain(t);
		transaction.setId(id);
		if (repository.findById(id) != null) {
			return TransactionMapper.toModel(repository.save(TransactionMapper.toDomain(t)));
		} else {
			return null;
		}
	}

}
