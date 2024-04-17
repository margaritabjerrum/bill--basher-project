package com.billbasher.controllers;

import com.billbasher.model.ExpenseDAO;
import com.billbasher.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/api/v1/expenses")
    public ResponseEntity<ExpenseDAO> createExpense(@RequestBody ExpenseDAO expense) {
        ExpenseDAO createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/expenses")
    public ResponseEntity<List<ExpenseDAO>> getAllExpenses() {
        List<ExpenseDAO> expenses = expenseService.getAllExpenses();
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }


}
