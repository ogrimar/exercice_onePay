package training.onepay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import training.onepay.domain.Transaction;
import training.onepay.mapper.TransactionMapper;
import training.onepay.model.OrderModel;
import training.onepay.model.Status;
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
		if (t.getStatus() != Status.NEW) {
			return null;
		}
		TransactionModel transaction = TransactionMapper.toDomain(t);
		return TransactionMapper.toModel(repository.save(transaction));
	}

	@Override
	public Transaction updateTransaction(Transaction t) {
		TransactionModel transaction = TransactionMapper.toDomain(t);
		transaction.setId(t.getId());
		Optional<TransactionModel> transConnueOpt = repository.findById(t.getId());
		if (transConnueOpt.isPresent()) {
			TransactionModel transConnue = transConnueOpt.get();
			if (transConnue.getStatus() == Status.CAPTURED
					|| (transConnue.getStatus() != Status.AUTHORIZED && transaction.getStatus() == Status.CAPTURED)
					|| !checkSameOrders(transaction, transConnue)) {
				return null;
			}
			return TransactionMapper.toModel(repository.save(transaction));
		} else {
			return null;
		}
	}

	private boolean checkSameOrders(TransactionModel t1, TransactionModel t2) {
		return (t1.getOrders().size() == t2.getOrders().size() && 
				t1.getOrders().containsAll(t2.getOrders()));
	}
}
