package com.billbasher.controllers;

import com.billbasher.model.ExpenseDAO;
import com.billbasher.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/api/v1/expenses")
    public ResponseEntity<ExpenseDAO> createExpense(@RequestBody ExpenseDAO expense) {
        ExpenseDAO createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }


    @DeleteMapping("/api/v1/expenses/remove/{id}")
    public ResponseEntity<String> removeExpenseById(@PathVariable("id") Long id) {
        try {
            expenseService.removeExpenseById(id);
            return ResponseEntity.ok("Expense successfully removed from event.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while removing Expense.");
        }
    }
}
