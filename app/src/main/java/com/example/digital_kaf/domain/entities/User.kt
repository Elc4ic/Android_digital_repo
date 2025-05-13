package com.example.digital_kaf.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

enum class Gender {
    MALE, FEMALE, OTHER
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    var id: UUID,
    var login: String = "",
    var nickname: String = "",
    var password: String = "",
    var gender: String = ""
)