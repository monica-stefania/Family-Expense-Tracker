package com.sd.laborator.expence.pojo

data class ExpenseResponse(
    var succes: Boolean,
    var message: String,
    var expense: Expense? = null,
    var expenses: List<Expense>? = null,
    var totalAmount: Double = 0.0
)
