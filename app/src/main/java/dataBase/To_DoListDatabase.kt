package dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example1.tablayout.To_DoList
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [ To_DoList::class ], version=1)
@TypeConverters(To_DoListTypeConverters::class)
abstract class To_DoListDatabase : RoomDatabase() {
    abstract fun to_DoListDao(): To_DoListDao


}
