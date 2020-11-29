package dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example1.tablayout.To_DoList
import java.util.*
@Dao
interface To_DoListDao {
    @Query("SELECT * FROM to_DoList WHERE status=0")
    fun getTo_DoLists(): LiveData<List<To_DoList>>
    @Query("SELECT * FROM to_DoList WHERE status=1")
    fun getProgress():  LiveData<List<To_DoList>>
    @Query("SELECT * FROM to_DoList WHERE status=2")
    fun getDoing():  LiveData<List<To_DoList>>
    @Query("SELECT * FROM to_DoList WHERE id=(:id)")
    fun getTo_DoList(id: UUID): LiveData<To_DoList?>
    @Insert
    fun addTo_DoList(to_DoList: To_DoList)
    @Update
    fun updateTo_DoList(to_DoList: To_DoList)
    @Delete
    fun deleteTo_DoList(to_DoList:To_DoList)





}