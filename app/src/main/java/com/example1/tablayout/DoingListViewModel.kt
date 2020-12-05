package com.example1.tablayout

import androidx.lifecycle.ViewModel

class DoingListViewModel: ViewModel() {
    private val doingRepositry = DoingRepositry.get()
    val  doingList = doingRepositry.getTo_DoLists()
    val  ProgressList = doingRepositry.getProgress()
    val  doneList = doingRepositry.getDoing()
    fun addTo_DoList(to_DoList: To_DoList) {
        doingRepositry.addTo_DoList(to_DoList)
    }
    fun updateTo_DoList(to_DoList: To_DoList) {
        doingRepositry.updateTo_DoList(to_DoList)
    }
}