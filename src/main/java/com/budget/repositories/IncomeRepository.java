package com.budget.repositories;

import com.budget.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT i.id FROM Income i")
    List<Long> findAllIds();

    @Query("SELECT i FROM Income i WHERE i.userId = :userId")
    List<Income> findAllUserIncomes(@Param("userId") Long userId);

    @Query("SELECT i FROM Income i WHERE i.userId = :userId AND i.id = :id")
    Income findIncomeByUserId(@Param("userId") Long userId, @Param("id") Long id);
}
