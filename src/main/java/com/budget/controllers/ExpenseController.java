package com.budget.controllers;

import com.budget.models.Expense;
import com.budget.models.ExpenseDto;
import com.budget.models.ExpenseDtoMapper;
import com.budget.models.User;
import com.budget.security.AuthenticationService;
import com.budget.services.ExpenseService;
import com.budget.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/budget/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseDtoMapper expenseDtoMapper;

    // User Permissions
    @GetMapping("/myself/{id}")
    public Expense getMyExpenseById(Long id) throws Exception {
        if (id != null) {
            User currentUser = authenticationService.getCurrentUser();
            return expenseService.findExpenseByUserId(currentUser.getId(), id);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/myself/all")
    public List<Expense> getAllMyExpenses() throws Exception {
        User currentUser = authenticationService.getCurrentUser();
        return expenseService.findAllUserExpenses(currentUser.getId());
    }

    @GetMapping("/myself/all/sorted")
    public List<Expense> getAllMyExpensesSorted() throws Exception {
        User currentUser = authenticationService.getCurrentUser();
        List<Expense> expenses = expenseService.findAllUserExpenses(currentUser.getId());
        Collections.sort(expenses, new Expense());
        return expenses;
    }

    @PutMapping("/myself/create")
    public void createExpense(@RequestBody ExpenseDto expenseDto) throws Exception {
        if (expenseDto != null) {
            User currentUser = authenticationService.getCurrentUser();
            userService.updateUserBalanceOnCreate(currentUser, expenseDto);
            expenseService.save(expenseDtoMapper.mapToDomain(expenseDto, currentUser.getId()));
        }
    }

    @PostMapping("/myself/update")
    public void updateMyExpense(@RequestBody Expense expense) throws Exception {
        if (expense != null) {
            User currentUser = authenticationService.getCurrentUser();
            Expense targetExpense = expenseService.findById(expense.getId());

            if (targetExpense == null || targetExpense.getUserId() != currentUser.getId()) {
                throw new ValidationException("Cannot delete income");
            }

            userService.updateUserBalanceOnUpdate(currentUser, targetExpense, expense);
            expenseService.save(expense);
        }
    }

    @DeleteMapping("/myself/delete/{id}")
    public void deleteMyExpense(@PathVariable Long id) throws Exception {
        if (id != null) {
            User currentUser = authenticationService.getCurrentUser();
            Expense targetExpense = expenseService.findById(id);

            if (targetExpense == null || targetExpense.getUserId() != currentUser.getId()) {
                throw new ValidationException("Cannot delete income");
            }

            userService.updateUserBalanceOnDelete(currentUser, targetExpense);
            expenseService.deleteById(id);
        }
    }

    // Admin Permission

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) throws Exception {
        if (id != null) {
            expenseService.findById(id);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/findAll/{userId}")
    public List<Expense> getAllUserExpenseById(@PathVariable Long userId) throws Exception {
        if (userId != null) {
            expenseService.findAllUserExpenses(userId);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/findAllById")
    public List<Expense> getAllById(List<Long> ids) throws Exception {
        if (ids != null) {
            return expenseService.findAllById(ids);
        }

        throw new ValidationException("Provided invalid IDs");
    }

    @GetMapping("/findAllIds")
    public List<Long> getAllIds() throws Exception {
        return expenseService.findAllIds();
    }

    @GetMapping("/findAll")
    public List<Expense> getAllExpenses() {
        return expenseService.findAll();
    }

    @PutMapping("/save")
    public void saveExpense(@RequestBody Expense expense) throws Exception {
        if (expense != null) {
            expenseService.save(expense);
        }
    }

    @PutMapping("/saveAll")
    public void saveAllExpense(@RequestBody List<Expense> expenses) throws Exception {
        if (expenses != null) {
            expenseService.saveAll(expenses);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExpense(@PathVariable Long id) throws Exception {
        if (id != null) {
            expenseService.deleteById(id);
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() throws Exception {
        expenseService.deleteAll();
    }
}
