package us.group14.backend.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.group14.backend.account.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    @Query("SELECT t FROM Transaction t JOIN t.sender a ON t.sender.id == a.id WHERE a.id == ?1")
//    Page<Transaction> findFromAccountWithPageable(Long accountId, Pageable pageable);
//
//    @Query("SELECT t FROM Transaction t JOIN t.sender a, t.recipient b ON t.sender.id == a.id OR t.recipient.id == b.id WHERE (a.id == ?1 OR b.id == ?1) AND t.id == ?2")
//    Optional<Transaction> findFromAccountWithId(Long accountId, Long id);
}
