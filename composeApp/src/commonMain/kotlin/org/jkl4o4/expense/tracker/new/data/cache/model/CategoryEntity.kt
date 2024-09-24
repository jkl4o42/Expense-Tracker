package org.jkl4o4.expense.tracker.new.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    private val id: Int = 0,
    private val name: String
)