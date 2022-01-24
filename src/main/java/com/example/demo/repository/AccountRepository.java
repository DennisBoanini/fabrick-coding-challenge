package com.example.demo.repository;

import com.example.demo.model.transaction.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<TransactionHistory, Long> {

	@Query(value = "SELECT UT.id FROM USER_TRANSACTION UT WHERE UT.accountId = :accountId AND UT.transactionId IN (:transactionIds)")
	List<Long> findAllByAccountIdAndTransactionIds(@Param("accountId") long accountId, @Param("transactionIds") List<String> transactionIds);

}
