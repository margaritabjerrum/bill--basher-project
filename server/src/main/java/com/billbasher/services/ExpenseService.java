package com.billbasher.services;

import com.billbasher.model.EventDAO;
import com.billbasher.model.ExpenseDAO;

import com.billbasher.repository.ExpenseRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExpenseService {
    @Autowired
    private ExpenseRep expenseRepository;

    public ExpenseDAO createExpense(ExpenseDAO expense) {
        return expenseRepository.save(expense);
    }


    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<ExpenseDAO> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void deleteExpensesByEvent(EventDAO event) {
        List<ExpenseDAO> expenses = expenseRepository.findByEventId(event);
        for (ExpenseDAO expense : expenses) {
            expenseRepository.delete(expense);
        }
    }


}


