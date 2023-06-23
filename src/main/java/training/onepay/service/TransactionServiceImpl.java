package training.onepay.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import training.onepay.domain.Transaction;
import training.onepay.errors.NotFoundException;
import training.onepay.errors.RGException;
import training.onepay.errors.TechnicalException;
import training.onepay.errors.WSException;
import training.onepay.mapper.TransactionMapper;
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
		try {
			Optional<TransactionModel> res = repository.findById(id);
			if (res.isEmpty()) {
				throw new NotFoundException();
			} else {
				return TransactionMapper.toModel(repository.findById(id).get());
			}
		} catch (Exception e) {
			if (e instanceof WSException) {
				throw e;
			} else {
				throw new TechnicalException(e);
			}
		}
	}

	@Override
	public List<Transaction> getAllTransactions() {
		try {
			List<Transaction> res = repository.findAll().stream().map(o -> TransactionMapper.toModel(o)).toList();
			if (CollectionUtils.isEmpty(res)) {
				throw new NotFoundException();
			}
			return res;
		} catch (Exception e) {
			if (e instanceof WSException) {
				throw e;
			} else {
				throw new TechnicalException(e);
			}
		}
	}

	@Override
	public Transaction createTransaction(Transaction t) {
		try {
			if (t.getStatus() != Status.NEW) {
				throw new RGException(422, "A new transaction's status must be NEW");
			}
			TransactionModel transaction = TransactionMapper.toDomain(t);
			return TransactionMapper.toModel(repository.save(transaction));
		} catch (Exception e) {
			if (e instanceof WSException) {
				throw e;
			} else {
				throw new TechnicalException(e);
			}
		}
	}

	@Override
	public Transaction updateTransaction(Transaction t) {
		try {
			TransactionModel transaction = TransactionMapper.toDomain(t);
			if (transaction.getId() == null) {
				throw new RGException(422, "The transaction's id can't be null on an update");
			}
			transaction.setId(t.getId());
			Optional<TransactionModel> transConnueOpt = repository.findById(t.getId());
			if (transConnueOpt.isPresent()) {
				TransactionModel transConnue = transConnueOpt.get();
				if (transConnue.getStatus() == Status.CAPTURED) {
					throw new RGException(422, "This transaction is already CAPTURED");
				}
				if (transConnue.getStatus() != Status.AUTHORIZED && transaction.getStatus() == Status.CAPTURED) {
					throw new RGException(422, "This transaction isn't AUTHORIZED");
				}
				if (!checkSameOrders(transaction, transConnue)) {
					throw new RGException(422, "a transaction's order can't be modified");
				}
				return TransactionMapper.toModel(repository.save(transaction));
			} else {
				throw new NotFoundException();
			}
		} catch (Exception e) {
			if (e instanceof WSException) {
				throw e;
			} else {
				throw new TechnicalException(e);
			}
		}
	}

	/**
	 * Compare 2 transactions to check if they have the same order list. <br/>
	 * The order's list sort is irrelevant.
	 * 
	 * @return true if the 2 transactions have the same orders
	 */
	private boolean checkSameOrders(TransactionModel t1, TransactionModel t2) {
		return (t1.getOrders().size() == t2.getOrders().size() && t1.getOrders().containsAll(t2.getOrders()));
	}
}
