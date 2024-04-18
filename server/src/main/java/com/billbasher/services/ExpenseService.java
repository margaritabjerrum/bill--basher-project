package com.billbasher.services;

import com.billbasher.dto.ExpenseDTO;
import com.billbasher.model.EventDAO;
import com.billbasher.model.ExpenseDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.ExpenseRep;
import com.billbasher.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRep expenseRepository;
    @Autowired
    private UserRep userRepository;
    @Autowired
    private EventRep eventRepository;

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


    public List<ExpenseDTO> getExpensesByUserIdAndEventId(Long userId, Long eventId) {
        Optional<UserDAO> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
        UserDAO user = userOptional.get();

        Optional<EventDAO> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new IllegalArgumentException("Event with ID " + eventId + " not found.");
        }
        EventDAO event = eventOptional.get();

        List<ExpenseDAO> expenses = expenseRepository.findByUserIdAndEventId(user, event);

        return expenses.stream()
                .map(expense -> {
                    ExpenseDTO expenseDTO = new ExpenseDTO();
                    expenseDTO.setExpenseId(expense.getExpenseId());
                    expenseDTO.setEventId(expense.getEventId().getEventId());
                    expenseDTO.setExpenseReason(expense.getExpenseReason());
                    expenseDTO.setAmountSpent(expense.getAmountSpent());
                    expenseDTO.setExpenseCreated(expense.getExpenseCreated());
                    return expenseDTO;
                })
                .collect(Collectors.toList());
    }
}

