package com.budget.models;

public class IncomeDto {

    private String name;
    private IncomeType type;
    private String description;
    private Long amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IncomeType getType() {
        return type;
    }

    public void setType(IncomeType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
