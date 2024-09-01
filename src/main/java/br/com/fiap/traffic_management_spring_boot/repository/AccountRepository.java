package br.com.fiap.traffic_management_spring_boot.repository;

import br.com.fiap.traffic_management_spring_boot.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {


    @Query("select u from Account u where u.createdAt between :init AND :end")
    Page<Account> findManyByCreatedAtPeriod(Date init, Date end, Pageable pagination);

    Optional<Account> findByEmail(String email);
}
