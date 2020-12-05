package com.example1.tablayout
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class To_DoList(@PrimaryKey val id: UUID = UUID.randomUUID(),
                     var title: String = "",
                     var details:String = "",
                     var date: Date=Date(),
                     var status: Int = 0
){

}