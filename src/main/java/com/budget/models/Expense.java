package com.budget.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;

@Entity
@Table(name = "expense")
public class Expense implements Comparator<Expense> {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long Id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "type")
    @NotNull
    private ExpenseType type;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    @NotNull
    private Long amount;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpenseType getType() {
        return type;
    }

    public void setType(ExpenseType type) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int compare(Expense expense1, Expense expense2) {
        return (int) (expense1.amount - expense2.amount);
    }
}
