package com.budget.repositories;

import com.budget.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT i.id FROM Expense i")
    List<Long> findAllIds();

    @Query("SELECT i FROM Expense i WHERE i.userId = :userId")
    List<Expense> findAllUserExpenses(@Param("userId") Long userId);

    @Query("SELECT i FROM Expense i WHERE i.userId = :userId AND i.id = :id")
    Expense findExpenseByUserId(@Param("userId") Long userId, @Param("id") Long id);
}
