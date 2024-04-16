package com.billbasher.repository;

import com.billbasher.model.ExpenseDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRep extends JpaRepository<ExpenseDAO, Long> {

}
