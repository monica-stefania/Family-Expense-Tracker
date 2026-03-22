package com.sd.laborator.expence.interfaces

import com.sd.laborator.expence.pojo.Expense
import org.springframework.data.jpa.repository.JpaRepository

interface ExpenceRespository: JpaRepository<Expense, Long> {
    fun findByMemberId(memberId: Long): List<Expense>
}