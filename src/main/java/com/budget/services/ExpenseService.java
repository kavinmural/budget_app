package com.budget.services;

import com.budget.models.Expense;
import com.budget.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense findExpenseByUserId(Long userId, Long id) { return expenseRepository.findExpenseByUserId(userId, id); }

    public Expense findById(Long id) { return expenseRepository.findById(id).get(); }

    public List<Expense> findAllById(List<Long> ids) { return expenseRepository.findAllById(ids); }

    public List<Expense> findAllUserExpenses(Long userId) { return expenseRepository.findAllUserExpenses(userId); }

    public List<Long> findAllIds() { return expenseRepository.findAllIds(); }

    public List<Expense> findAll() { return expenseRepository.findAll(); }

    public void save(Expense expense) { expenseRepository.save(expense); }

    public void saveAll(List<Expense> expenses) { expenseRepository.saveAll(expenses); }

    public void deleteById(Long id) { expenseRepository.deleteById(id); }

    public void deleteAll() { expenseRepository.deleteAll(); }
}
