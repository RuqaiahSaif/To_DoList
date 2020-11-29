package com.example1.tablayout
import dataBase.To_DoListDatabase
import android.content.Context
import androidx.lifecycle.LiveData

import androidx.room.Room
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "doing-database"
class DoingRepositry private constructor(context: Context){
    private val database : To_DoListDatabase =Room.databaseBuilder(
        context.applicationContext,
        To_DoListDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val to_DoListDao =database.to_DoListDao()
    fun getTo_DoLists(): LiveData<List<To_DoList>> = to_DoListDao.getTo_DoLists()
    fun getProgress(): LiveData<List<To_DoList>> = to_DoListDao.getProgress()
    fun getDoing(): LiveData<List<To_DoList>> = to_DoListDao.getDoing()
    fun getTo_DoList(id: UUID): LiveData<To_DoList?> = to_DoListDao.getTo_DoList(id)
    private val executor = Executors.newSingleThreadExecutor()

    fun addTo_DoList(to_DoList: To_DoList) {
        executor.execute {
            to_DoListDao.addTo_DoList(to_DoList )
        }
    }
    companion object {
        private var INSTANCE:DoingRepositry ? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = DoingRepositry(context)
            }
        }
        fun get(): DoingRepositry {
            return INSTANCE?: throw IllegalStateException("DoingRepositry must be initialized")
        }
    }
}
