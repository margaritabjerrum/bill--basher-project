package com.billbasher;

import com.billbasher.model.EventDAO;
import com.billbasher.model.ExpenseDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.ExpenseRep;
import com.billbasher.repository.UserRep;
import com.billbasher.services.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    public void testCreateExpense() {
        ExpenseDAO expense = new ExpenseDAO();
        expenseService.createExpense(expense);
        verify(expenseRepository, times(1)).save(expense);
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
    public void testFindExpensesByEventIdNoExistExpenses() {
        EventDAO event = new EventDAO();
        event.setEventId(1L);
        when(expenseRepository.findByEventId(event)).thenReturn(new ArrayList<>());
        assertThrows(NoSuchElementException.class, () -> {
            expenseService.findExpensesByEventId(event);
        });
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
    public void testDeleteExpensesByEventNullEventId() {
        EventDAO event = mock(EventDAO.class);
        when(event.getEventId()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            expenseService.deleteExpensesByEvent(event);
        });

        verify(expenseRepository, never()).findByEventId(any(EventDAO.class));

        verify(expenseRepository, never()).delete(any(ExpenseDAO.class));
    }
}