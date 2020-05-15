package com.budget.services;

import com.budget.models.Income;
import com.budget.repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public Income findIncomeByUserId(Long userId, Long id) { return incomeRepository.findIncomeByUserId(userId, id); }

    public Income findById(Long id) { return incomeRepository.findById(id).get(); }

    public List<Income> findAllById(List<Long> ids) { return incomeRepository.findAllById(ids); }

    public List<Income> findAllUserIncomes(Long userId) { return incomeRepository.findAllUserIncomes(userId); }

    public List<Long> findAllIds() { return incomeRepository.findAllIds(); }

    public List<Income> findAll() { return incomeRepository.findAll(); }

    public void save(Income income) { incomeRepository.save(income); }

    public void saveAll(List<Income> incomes) { incomeRepository.saveAll(incomes); }

    public void deleteById(Long id) { incomeRepository.deleteById(id); }

    public void deleteAll() { incomeRepository.deleteAll(); }
}
