package com.billbasher;

import com.billbasher.model.EventDAO;
import com.billbasher.model.ExpenseDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.model.UserEventDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.ExpenseRep;
import com.billbasher.repository.UserEventRep;
import com.billbasher.repository.UserRep;
import com.billbasher.services.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ExpenseServiceTest {

    @Mock
    private ExpenseRep expenseRepository;

    @Mock
    private UserRep userRepository;

    @Mock
    private EventRep eventRepository;

    @Mock
    private UserEventRep userEventRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    public void testCreateExpense() {
        UserDAO user = new UserDAO();
        user.setUserId(1L);
        EventDAO event = new EventDAO();
        event.setEventId(1L);
        event.setUserId(user);
        UserEventDAO userEvent = new UserEventDAO();
        userEvent.setId(1L);
        userEvent.setTotal(0);
        userEvent.setUserId(user);
        userEvent.setEventId(event);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(eventRepository.save(event)).thenReturn(event);
        Mockito.when(userEventRepository.save(userEvent)).thenReturn(userEvent);

        ExpenseDAO expense = new ExpenseDAO();
        expense.setUserId(user);
        expense.setEventId(event);
        expense.setAmountSpent(9D);

        Mockito.when(expenseRepository.save(expense)).thenReturn(expense);
        expenseService.createExpense(expense);

        Mockito.verify(expenseRepository, Mockito.times(1)).save(expense);
    }
    @Test
    public void testRemoveExpenseById() {
        Long id = 1L;
        expenseService.removeExpenseById(id);
        verify(expenseRepository, times(1)).deleteById(id);
    }
    @Test
    public void testFindExpensesByEventId() {
        // Given
        EventDAO event = new EventDAO();
        event.setEventId(1L);

        List<ExpenseDAO> expenses = new ArrayList<>();
        expenses.add(new ExpenseDAO());
        expenses.add(new ExpenseDAO());

        when(expenseRepository.findByEventId(event)).thenReturn(expenses);

        List<ExpenseDAO> result = expenseService.findExpensesByEventId(event);

        assertEquals(expenses.size(), result.size());
        verify(expenseRepository, times(1)).findByEventId(event);
    }

    @Test
    public void testUpdateExpenseById() {
        Long expenseId = 1L;
        ExpenseDAO expense = new ExpenseDAO();
        expense.setExpenseId(expenseId);

        when(expenseRepository.save(expense)).thenReturn(expense);

        ExpenseDAO updatedExpense = expenseService.updateExpenseById(expenseId, expense);

        assertNotNull(updatedExpense);
        assertEquals(expenseId, updatedExpense.getExpenseId());
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    public void testUpdateExpenseByIdInvalidId() {
        Long expenseId = null;
        ExpenseDAO expense = new ExpenseDAO();

        assertThrows(IllegalArgumentException.class, () -> {
            expenseService.updateExpenseById(expenseId, expense);
        });
    }

    @Test
    public void testUpdateExpenseByIdInvalidExpense() {
        Long expenseId = 1L;
        ExpenseDAO expense = null;

        assertThrows(IllegalArgumentException.class, () -> {
            expenseService.updateExpenseById(expenseId, expense);
        });
    }

    @Test
    public void testGetExpensesByUserIdAndEventIdNonExistUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            expenseService.getExpensesByUserIdAndEventId(1L, 1L);
        });

        verify(expenseRepository, never()).findByUserIdAndEventId(any(UserDAO.class), any(EventDAO.class));
    }

    @Test
    public void testGetExpensesByUserIdAndEventIdNonExist() {
        UserDAO user = new UserDAO();
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            expenseService.getExpensesByUserIdAndEventId(1L, 1L);
        });

        verify(expenseRepository, never()).findByUserIdAndEventId(any(UserDAO.class), any(EventDAO.class));
    }
    @Test
    public void testDeleteExpensesByEvent() {
        EventDAO event = new EventDAO();
        event.setEventId(1L);

        List<ExpenseDAO> expenses = new ArrayList<>();
        expenses.add(new ExpenseDAO());
        expenses.add(new ExpenseDAO());

        Mockito.when(expenseRepository.findByEventId(event)).thenReturn(expenses);

        expenseService.deleteExpensesByEvent(event);

        Mockito.verify(expenseRepository, Mockito.times(1)).deleteAll(expenses);
    }
}