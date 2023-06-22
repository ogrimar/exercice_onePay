package training.onepay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import training.onepay.model.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {

}
