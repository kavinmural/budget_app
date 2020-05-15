package com.budget.services;

import com.budget.models.*;
import com.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User findByUsername(String username) { return userRepository.findByUsername(username); }

    public User findByEmail(String email) { return userRepository.findByEmail(email); }

    public List<User> findAllById(List<Long> ids) { return userRepository.findAllById(ids); }

    public List<User> findAll() { return userRepository.findAll(); }

    public List<User> findAllActive() { return userRepository.findAllActive(); }

    public List<User> findAllInactive() { return userRepository.findAllInactive(); }

    public List<User> findAllAdmins() { return userRepository.findAllAdmins(); }

    public List<User> findAllUsers() { return userRepository.findAllUsers(); }

    public List<Long> findAllUserIds() { return userRepository.findAllUserIds(); }

    public List<String> findAllUserEmails() { return userRepository.findAllUserEmails(); }

    public void save(User user) { userRepository.save(user); }

    public void saveAll(List<User> users) { userRepository.saveAll(users); }

    public void deleteById(Long id) { userRepository.deleteById(id); }

    public void deleteAll() { userRepository.deleteAll(); }

    public void activateUser(User user) {
        user.setActive(true);
        userRepository.save(user);
    }

    public void deactivateUser(User user) {
        user.setActive(false);
        userRepository.save(user);
    }

    public void updateUserBalanceOnCreate(User user, IncomeDto incomeDto) {
        user.setBalance(user.getBalance() + incomeDto.getAmount());
        userRepository.save(user);
    }

    public void updateUserBalanceOnUpdate(User user, Income prevIncome, Income newIncome) {
        Long incomeDiff = prevIncome.getAmount() - newIncome.getAmount();
        user.setBalance(prevIncome.getAmount() + incomeDiff);
        userRepository.save(user);
    }

    public void updateUserBalanceOnDelete(User user, Income income) {
        user.setBalance(user.getBalance() - income.getAmount());
        userRepository.save(user);
    }

    public void updateUserBalanceOnCreate(User user, ExpenseDto expenseDto) {
        user.setBalance(user.getBalance() + expenseDto.getAmount());
        userRepository.save(user);
    }

    public void updateUserBalanceOnUpdate(User user, Expense prevExpense, Expense newExpense) {
        Long incomeDiff = prevExpense.getAmount() - newExpense.getAmount();
        user.setBalance(prevExpense.getAmount() + incomeDiff);
        userRepository.save(user);
    }

    public void updateUserBalanceOnDelete(User user, Expense expense) {
        user.setBalance(user.getBalance() - expense.getAmount());
        userRepository.save(user);
    }
}
