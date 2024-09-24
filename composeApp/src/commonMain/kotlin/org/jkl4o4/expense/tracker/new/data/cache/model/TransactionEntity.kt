package org.jkl4o4.expense.tracker.new.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    private val id: Int = 0,
    private val categoryEntity: CategoryEntity,
    private val description: String,
    private val amount: Double,
    private val paymentType: PaymentType,
    private val wallet: WalletEntity
)