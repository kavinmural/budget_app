package com.budget.models;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ExpenseDtoMapper {

    public Expense mapToDomain(ExpenseDto expenseDto, Long userId) {
        Expense expense = new Expense();
        expense.setName(expenseDto.getName());
        expense.setType(expenseDto.getType());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setCreatedDate(LocalDate.now().toString());
        expense.setUserId(userId);

        return expense;
    }

    public ExpenseDto mapToDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setName(expense.getName());
        expenseDto.setType(expense.getType());
        expenseDto.setDescription(expense.getDescription());
        expenseDto.setAmount(expense.getAmount());

        return expenseDto;
    }
}
