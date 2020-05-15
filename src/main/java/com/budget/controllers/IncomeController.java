package com.budget.controllers;

import com.budget.models.Income;
import com.budget.models.IncomeDto;
import com.budget.models.IncomeDtoMapper;
import com.budget.models.User;
import com.budget.security.AuthenticationService;
import com.budget.services.IncomeService;
import com.budget.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/budget/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private IncomeDtoMapper incomeDtoMapper;

    // User Permissions
    @GetMapping("/myself/{id}")
    public Income getMyIncomeById(Long id) throws Exception {
        if (id != null) {
            User currentUser = authenticationService.getCurrentUser();
            return incomeService.findIncomeByUserId(currentUser.getId(), id);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/myself/all")
    public List<Income> getAllMyIncomes() throws Exception {
        User currentUser = authenticationService.getCurrentUser();
        return incomeService.findAllUserIncomes(currentUser.getId());
    }

    @GetMapping("/myself/all/sorted")
    public List<Income> getAllMyIncomesSorted() throws Exception {
        User currentUser = authenticationService.getCurrentUser();
        List<Income> incomes = incomeService.findAllUserIncomes(currentUser.getId());
        Collections.sort(incomes, new Income());
        return incomes;
    }

    @PutMapping("/myself/create")
    public void createIncome(@RequestBody IncomeDto incomeDto) throws Exception {
        if (incomeDto != null) {
            User currentUser = authenticationService.getCurrentUser();
            userService.updateUserBalanceOnCreate(currentUser, incomeDto);
            incomeService.save(incomeDtoMapper.mapToDomain(incomeDto, currentUser.getId()));
        }
    }

    @PostMapping("/myself/update")
    public void updateMyIncome(@RequestBody Income income) throws Exception {
        if (income != null) {
            User currentUser = authenticationService.getCurrentUser();
            Income targetIncome = incomeService.findById(income.getId());

            if (targetIncome == null || targetIncome.getUserId() != currentUser.getId()) {
                throw new ValidationException("Cannot update income");
            }

            userService.updateUserBalanceOnUpdate(currentUser, targetIncome, income);
            incomeService.save(income);
        }
    }

    @DeleteMapping("/myself/delete/{id}")
    public void deleteMyIncome(@PathVariable Long id) throws Exception {
        if (id != null) {
            User currentUser = authenticationService.getCurrentUser();
            Income targetIncome = incomeService.findById(id);

            if (targetIncome == null || targetIncome.getUserId() != currentUser.getId()) {
                throw new ValidationException("Cannot delete income");
            }

            userService.updateUserBalanceOnDelete(currentUser, targetIncome);
            incomeService.deleteById(id);
        }
    }

    // Admin Permissions

    @GetMapping("/{id}")
    public Income getIncomeById(@PathVariable Long id) throws Exception {
        if (id != null) {
            return incomeService.findById(id);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/findAll/{userId}")
    public List<Income> getAllUserIncomeById(@PathVariable Long userId) throws Exception {
        if (userId != null) {
            return incomeService.findAllUserIncomes(userId);
        }

        throw new ValidationException("Provided invalid ID");
    }

    @GetMapping("/findAllById")
    public List<Income> getAllById(List<Long> ids) throws Exception {
        if (ids != null) {
            return incomeService.findAllById(ids);
        }

        throw new ValidationException("Provided invalid IDs");
    }

    @GetMapping("/findAllIds")
    public List<Long> getAllIds() throws Exception {
        return incomeService.findAllIds();
    }

    @GetMapping("/findAll")
    public List<Income> getAllIncomes() {
        return incomeService.findAll();
    }

    @PutMapping("/save")
    public void saveIncome(@RequestBody Income income) throws Exception {
        if (income != null) {
            incomeService.save(income);
        }
    }

    @PutMapping("/saveAll")
    public void saveAllIncome(@RequestBody List<Income> incomes) throws Exception {
        if (incomes != null) {
            incomeService.saveAll(incomes);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteIncome(@PathVariable Long id) throws Exception {
        if (id != null) {
            incomeService.deleteById(id);
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() throws Exception {
        incomeService.deleteAll();
    }
}
