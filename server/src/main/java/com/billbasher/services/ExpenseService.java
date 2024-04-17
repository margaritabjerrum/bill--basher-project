package com.billbasher.services;

import com.billbasher.model.EventDAO;
import com.billbasher.model.ExpenseDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.ExpenseRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.billbasher.model.ExpenseDAO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRep expenseRepository;

    public ExpenseDAO createExpense(ExpenseDAO expense) {

        return expenseRepository.save(expense);
    }

    public void removeExpenseById(@PathVariable("id") Long id) {
        expenseRepository.deleteById(id);
    }

    public ExpenseDAO findExpenseById(@PathVariable("id") Long id) {
        return expenseRepository.findById(id).get();

    }
    public List<ExpenseDAO> findExpensesByEventId(EventDAO  event) {
        return expenseRepository.findByEventId(event);
    }

    public ExpenseDAO updateExpenseById(Long id, ExpenseDAO expense) {

        return expenseRepository.save(expense);
    }

    public void deleteEventById(Long id) {

        expenseRepository.deleteById(id);
    }

    public List<ExpenseDAO> getAllExpenseByEventId() {
        return expenseRepository.findAll();
    }
}

