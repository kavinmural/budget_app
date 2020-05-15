package com.budget.models;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class IncomeDtoMapper {

    public Income mapToDomain(IncomeDto incomeDto, Long userId) {
        Income income = new Income();
        income.setName(incomeDto.getName());
        income.setType(incomeDto.getType());
        income.setDescription(incomeDto.getDescription());
        income.setAmount(incomeDto.getAmount());
        income.setCreatedDate(LocalDate.now().toString());
        income.setUserId(userId);

        return income;
    }

    public IncomeDto mapToDto(Income income) {
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setName(income.getName());
        incomeDto.setType(income.getType());
        incomeDto.setDescription(income.getDescription());
        incomeDto.setAmount(income.getAmount());

        return incomeDto;
    }
}
